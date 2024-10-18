package com.yoon.reward.user.query.controller;

import com.yoon.reward.user.query.dto.UserLoginDTO;
import com.yoon.reward.user.query.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    //스프링 시큐리티로 인해 아래 코드 필요없음
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO){
//        if (userLoginDTO == null || userLoginDTO.getUserId() == null || userLoginDTO.getUserPassword() == null) {
//            return new ResponseEntity<>("유저 정보가 없습니다.", HttpStatus.BAD_REQUEST);
//        }
//
//        boolean isAuthenticated = userInfoService.loginUser(userLoginDTO);
//
//        if(isAuthenticated){
//            return ResponseEntity.ok ("로그인이 되었습니다.");
//        } else{
//            return new ResponseEntity<>("로그인에 실패했습니다.",HttpStatus.UNAUTHORIZED);
//        }
//    }

    // 인증된 사용자가 접근할 수 있는 보호된 리소스(JWT용)
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

}
