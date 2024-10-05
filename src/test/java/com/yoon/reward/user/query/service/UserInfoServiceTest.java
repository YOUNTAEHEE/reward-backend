package com.yoon.reward.user.query.service;

import com.yoon.reward.user.query.dto.UserLoginDTO;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserInfoServiceTest {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private UserInfoService userInfoService;
    @DisplayName("login 테스트")
    @Test
    public void loginUser(){
        String userId = "string";
        String userPassword = "string";
        UserLoginDTO userLoginDTO = new UserLoginDTO(userId, userPassword);
        Assertions.assertDoesNotThrow(
                ()->{
                    userInfoService.loginUser(userLoginDTO);
                    System.out.printf("로그인 성공");
                }
        );
    }

}