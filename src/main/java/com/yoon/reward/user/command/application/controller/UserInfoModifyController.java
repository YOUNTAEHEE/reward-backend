package com.yoon.reward.user.command.application.controller;

import com.yoon.reward.user.command.application.dto.UserInfoModifyDTO;
import com.yoon.reward.user.command.application.service.UserInfoModifyService;
import com.yoon.reward.user.query.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my/info")
public class UserInfoModifyController {
    private final UserInfoModifyService userInfoModifyService;

    public UserInfoModifyController(UserInfoModifyService userInfoModifyService) {
        this.userInfoModifyService = userInfoModifyService;
    }

    @PostMapping()
    public ResponseEntity<UserInfoModifyDTO> modifyUserInfo(@RequestBody UserInfoModifyDTO userInfoModifyDTO){
        try{
            UserInfoModifyDTO updateUserInfo = userInfoModifyService.modifyUserInfo(userInfoModifyDTO);
            return ResponseEntity.ok (updateUserInfo);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
