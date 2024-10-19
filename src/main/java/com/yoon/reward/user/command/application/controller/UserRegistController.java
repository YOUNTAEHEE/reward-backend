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
      }catch (IllegalStateException e) {
          throw new IllegalStateException(e.getMessage()); // 이미 존재하는 아이디의 경우 409 상태 코드 사용
      } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException(e.getMessage()); // 필수 항목 누락의 경우 400 상태 코드 사용
      } catch (Exception e) {
          throw new RuntimeException("서버 에러가 발생했습니다. 다시 시도해 주세요.");
      }
    }
}
