package com.yoon.reward.user.query.controller;
import com.yoon.reward.jwt.JWTUtil;
import com.yoon.reward.user.command.domain.aggregate.UserRole;
import com.yoon.reward.user.query.dto.CustomUserDetails;
import com.yoon.reward.user.query.dto.UserLoginDTO;
import com.yoon.reward.user.query.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class JWTController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserInfoService userInfoService;
    @Autowired
    public JWTController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserInfoService userInfoService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userInfoService = userInfoService;
    }

    // 로그인 요청을 처리하여 JWT 토큰을 반환
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            // 에러 메시지를 담을 리스트 선언
            List<String> errors = new ArrayList<>();

            // userLoginDTO가 null인 경우 처리
            if (userLoginDTO == null) {
                return ResponseEntity.status(400).body("로그인 정보가 입력되지 않았습니다.");
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
                return ResponseEntity.status(400).body(errors);
            }

            String userId = userLoginDTO.getUserId();
            String rawPassword = userLoginDTO.getUserPassword();

            // 사용자 정보 조회
            CustomUserDetails userDetails = (CustomUserDetails) userInfoService.loadUserByUsername(userId);
            String encodedPassword = userDetails.getPassword();

            // 비밀번호 검증
            if (!userInfoService.checkPassword(rawPassword, encodedPassword)) {
                return ResponseEntity.status(401).body("비밀번호가 일치하지 않습니다.");
            }

            // 인증된 사용자 정보
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            GrantedAuthority authority = authorities.iterator().next(); // 첫 번째 권한만 가져옴 (단일 권한인 경우)

            // String으로 권한을 받아서 UserRole로 변환
            UserRole role = UserRole.valueOf(authority.getAuthority());

            // JWT 토큰 생성
            String token = jwtUtil.createJwt(userId, role, 60 * 60 * 1000L); // 1시간 만료

            // 토큰을 Response로 반환
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("JWT Token 발급 완료");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("인증 실패: " + e.getMessage());
        }
    }

    // 토큰 유효성 검사 및 사용자 정보 확인
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("토큰이 없습니다.");
        }

        String token = authorizationHeader.substring(7); // "Bearer " 제거 후 토큰만 추출
        if (jwtUtil.isExpired(token)) {
            return ResponseEntity.status(401).body("토큰이 만료되었습니다.");
        }

        // 토큰에서 사용자 ID 및 역할(Role) 추출
        String userId = jwtUtil.getUserId(token);
        UserRole role = jwtUtil.getRole(token);

        return ResponseEntity.ok().body("유효한 토큰: 사용자 ID = " + userId + ", 권한 = " + role);
    }

    // 로그아웃 처리 (옵션)
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("로그아웃 완료");
    }
}
