package com.yoon.reward.user.query.controller;

import com.yoon.reward.user.query.dto.UserLoginDTO;
import com.yoon.reward.user.query.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        boolean isAuthenticated = userInfoService.loginUser(userLoginDTO);

        if(isAuthenticated){
            return ResponseEntity.ok ("로그인이 되었습니다.");
        } else{
            return new ResponseEntity<>("로그인에 실패했습니다.",HttpStatus.UNAUTHORIZED);
        }
     }
}
