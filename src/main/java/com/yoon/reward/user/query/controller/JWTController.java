package com.yoon.reward.user.query.controller;
import com.yoon.reward.exception.CustomBusinessException;
import com.yoon.reward.jwt.JWTUtil;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.aggregate.UserRole;
import com.yoon.reward.user.query.dto.CustomUserDetails;
import com.yoon.reward.user.query.dto.UserLoginDTO;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import com.yoon.reward.user.query.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class JWTController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserInfoService userInfoService;
    private final UserQueryRepository userQueryRepository;

    public JWTController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserInfoService userInfoService,
                         UserQueryRepository userQueryRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userInfoService = userInfoService;
        this.userQueryRepository = userQueryRepository;
    }

    // 로그인 요청을 처리하여 JWT 토큰을 반환
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            // 에러 메시지를 담을 리스트 선언
            List<String> errors = new ArrayList<>();

            // userLoginDTO가 null인 경우 처리
            if (userLoginDTO == null) {
                throw new IllegalArgumentException("로그인 정보가 입력되지 않았습니다.");
            }

            // 아이디 검증
            if (userLoginDTO.getUserId() == null || userLoginDTO.getUserId().isEmpty()) {
                errors.add("아이디를 입력해주세요.");
            }

            // 비밀번호 검증
            if (userLoginDTO.getUserPassword() == null || userLoginDTO.getUserPassword().isEmpty()) {
                errors.add("비밀번호를 입력해주세요.");
            }

            // 에러가 있을 경우, 에러 메시지를 반환
            if (!errors.isEmpty()) {
                throw new IllegalArgumentException(String.join(", ", errors));
            }

            String userId = userLoginDTO.getUserId();

            // 사용자 정보 조회
            Optional<User> optionalUser = userQueryRepository.findByUserId(userLoginDTO.getUserId());

            // 회원 정보가 없을 경우 처리
            if (optionalUser.isEmpty()) {
                throw new CustomBusinessException("회원 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            }

            // 사용자 정보 조회
            CustomUserDetails userDetails = (CustomUserDetails) userInfoService.loadUserByUsername(userId);

            // userDetails가 null일 경우 예외 처리
            if (userDetails == null) {
                throw new CustomBusinessException("회원 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            }

            String rawPassword = userLoginDTO.getUserPassword();
            String encodedPassword = userDetails.getPassword();

            // 비밀번호 검증
            if (!userInfoService.checkPassword(rawPassword, encodedPassword)) {
                throw new CustomBusinessException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
            }



            // 인증된 사용자 정보
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            GrantedAuthority authority = authorities.iterator().next(); // 첫 번째 권한만 가져옴 (단일 권한인 경우)

            // String으로 권한을 받아서 UserRole로 변환
            UserRole role = UserRole.valueOf(authority.getAuthority());

            // JWT 토큰 생성
            String token = jwtUtil.createJwt(userId, role, 60 * 60 * 211000L);

//            Optional<User> optionalUser = userQueryRepository.findByUserId(userLoginDTO.getUserId());

            Map<String, Object> loginUserInfo = new HashMap<>();

            optionalUser.ifPresent(user -> {
                loginUserInfo.put("userId", userDetails.getUsername());
                loginUserInfo.put("userNickname", user.getUserNickname());
                loginUserInfo.put("userPoint", user.getUserPoint());
            });

            // 토큰을 Response로 반환
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(Map.of("token", token,"loginUserInfo", loginUserInfo,"successMessage", "로그인이 되었습니다."));
        } catch (CustomBusinessException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
//            throw new RuntimeException("인증 실패: " + e.getMessage());
            throw e;
        }
    }

    // 토큰 유효성 검사 및 사용자 정보 확인
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new CustomBusinessException("토큰이 없습니다.", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7); // "Bearer " 제거 후 토큰만 추출
        if (jwtUtil.isExpired(token)) {
            throw new CustomBusinessException("토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED);
        }

        // 토큰에서 사용자 ID 및 역할(Role) 추출
        String userId = jwtUtil.getUserId(token);
        UserRole role = jwtUtil.getRole(token);

        return ResponseEntity.ok().body("유효한 토큰: 사용자 ID = " + userId + ", 권한 = " + role);
    }

    // 로그아웃 처리 (옵션)
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok("로그아웃 완료");
        }catch(Exception e) {
            throw new RuntimeException("로그아웃 중 오류가 발생했습니다.");
        }
    }
}
