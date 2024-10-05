package com.yoon.reward.user.command.application.service;

import com.fasterxml.jackson.core.JsonPointer;
import com.yoon.reward.user.command.application.dto.UserInfoModifyDTO;
import com.yoon.reward.user.command.application.dto.UserRegistDTO;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.repository.UserCommandRepository;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserInfoModifyServiceTest {
    @Autowired
    private UserCommandRepository userCommandRepository;
    @Autowired
    private UserInfoModifyService userInfoModifyService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRegistService userRegistService;

    @DisplayName("나의 정보 수정 테스트")
    @Test
    public void modifyUserInfo(){
        UserRegistDTO userRegistDTO = new UserRegistDTO("string", "string", "string", "string", "string", "string", "string", "string");
        userRegistService.registUser(userRegistDTO);

        String userId = "string";
        String userPassword = "newstring";
        String userNickname = "newstring";
        String userPhone = "newstring";
        UserInfoModifyDTO userInfoModifyDTO = new UserInfoModifyDTO(userId, userPassword, userNickname, userPhone);

        UserInfoModifyDTO result = Assertions.assertDoesNotThrow(
                ()-> userInfoModifyService.modifyUserInfo(userInfoModifyDTO));

        assertEquals("newstring", result.getUserNickname());
        assertTrue(passwordEncoder.matches("newstring", result.getUserPassword()),
                "비밀번호가 암호화된 값과 일치하지 않음");
        assertEquals("newstring", result.getUserPhone());

    }

}