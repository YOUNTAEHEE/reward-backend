package com.yoon.reward.user.command.application.controller;

import com.yoon.reward.user.command.application.dto.UserRegistDTO;
import com.yoon.reward.user.command.application.service.UserRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistController {

    private final UserRegistService userRegistService;

    public UserRegistController(UserRegistService userRegistService) {
        this.userRegistService = userRegistService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> registUser(@RequestBody UserRegistDTO userRegistDTO){
      try{
          userRegistService.registUser(userRegistDTO);
          return new ResponseEntity<>("가입되었습니다!",HttpStatus.CREATED);
      }
      catch (Exception e){
          return new ResponseEntity<>("가입에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
