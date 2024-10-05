package com.yoon.reward.user.command.application.service;

import com.yoon.reward.user.command.application.dto.UserRegistDTO;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.repository.UserCommandRepository;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRegistServiceTest {

    @Autowired
    private UserCommandRepository userCommandRepository;

    @Autowired
    private UserRegistService userRegistService;

    private static Stream<Arguments> newUser() {
        return Stream.of(
                Arguments.of(new UserRegistDTO("id", "password", "홍길동", "홍홍홍", "01012345678", "홍길동", "농협", "123123123"))
        );
    }

    @DisplayName("가입 테스트")
    @ParameterizedTest
    @MethodSource("newUser")
    public void registUser(UserRegistDTO userRegistDTO){

        Assertions.assertDoesNotThrow(()->{
                userRegistService.registUser(userRegistDTO);
                System.out.println("User registration successful.");
        });


    }
}