package com.yoon.reward.point.command.application.service;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.command.domain.aggregate.PointAction;
import com.yoon.reward.user.command.application.dto.UserInfoModifyDTO;
import com.yoon.reward.user.command.application.dto.UserRegistDTO;
import com.yoon.reward.user.command.application.service.UserRegistService;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.csrf.CsrfToken;

import java.util.Optional;
import java.util.stream.Stream;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UpdatePointServiceTest {
    @Autowired
    private UserRegistService userRegistService;
    @Autowired
    private UpdatePointService updatePointService;

    @Autowired
    private UserQueryRepository userQueryRepository;

    private static Stream<Arguments> testPointDelta(){
        return Stream.of(
                Arguments.of(new PointDetailDTO("string", PointAction.POINT_DEPOSIT, now(), 1000L)),
                Arguments.of(new PointDetailDTO("string", PointAction.POINT_WITHDRAW, now(), 500L)),
                Arguments.of(new PointDetailDTO("string", PointAction.POINT_DEPOSIT, now(), 1000L))

        );
    }


    @DisplayName("입출금 테스트, 총 포인트 내역 반환")
    @ParameterizedTest
    @MethodSource("testPointDelta")
    public void processPointTransaction(PointDetailDTO pointDetailDTO){
        UserRegistDTO userRegistDTO = new UserRegistDTO("string", "string", "string", "string", "string", "string", "string", "string");
        userRegistService.registUser(userRegistDTO);

     Assertions.assertDoesNotThrow(
                () -> updatePointService.processPointTransaction(pointDetailDTO)
        );

        User user = userQueryRepository.findByUserId(pointDetailDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저 정보가 없습니다."));

        assertEquals(pointDetailDTO.getPointDelta(), user.getUserPoint());
    }

}