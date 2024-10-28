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

import java.util.Collections;

@RestController
@RequestMapping("/my/info")
public class UserInfoModifyController {
    private final UserInfoModifyService userInfoModifyService;

    public UserInfoModifyController(UserInfoModifyService userInfoModifyService) {
        this.userInfoModifyService = userInfoModifyService;
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyUserInfo(@RequestBody UserInfoModifyDTO userInfoModifyDTO) {
        try {
            userInfoModifyService.modifyUserInfo(userInfoModifyDTO);
            return ResponseEntity.ok("정보가 성공적으로 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "서버 오류가 발생했습니다: " + e.getMessage()));
        }
    }
}