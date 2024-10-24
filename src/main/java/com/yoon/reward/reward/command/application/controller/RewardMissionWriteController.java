package com.yoon.reward.reward.command.application.controller;

import com.yoon.reward.exception.CustomBusinessException;
import com.yoon.reward.reward.command.application.service.RewardMissionWriteService;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import org.springframework.aop.AopInvocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/my/reward")
public class RewardMissionWriteController {
    private final RewardMissionWriteService rewardMissionWriteService;

    public RewardMissionWriteController(RewardMissionWriteService rewardMissionWriteService) {
        this.rewardMissionWriteService = rewardMissionWriteService;
    }

    //미션 등록
//    @PostMapping("/write")
//    public ResponseEntity<?> rewardMissionWrite(@RequestBody RewardMissionDTO rewardMissionDTO){
//        if (rewardMissionDTO == null) {
//            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "리워드 정보를 입력해주세요"));
//        }
//
//        try{
//            rewardMissionWriteService.rewardMissionWrite(rewardMissionDTO);
//            return ResponseEntity.ok("리워드를 등록했습니다");
//        } catch (IllegalArgumentException e) {
//            // 예외를 직접 던지고 GlobalExceptionHandler에서 처리
//            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
//        }  catch (Exception e) {
//            // 일반적인 서버 오류 처리
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("message", "서버 오류가 발생했습니다: " + e.getMessage()));
//        }
//    }
    @PostMapping("/write")
    public ResponseEntity<String> rewardMissionWrite(@RequestBody RewardMissionDTO rewardMissionDTO) {
        rewardMissionWriteService.rewardMissionWrite(rewardMissionDTO);
        return ResponseEntity.ok("리워드를 등록했습니다.");
    }
    //미션수정
    @PostMapping("/modify")
    public ResponseEntity<String> rewardMissionModify(@RequestBody RewardMissionDTO rewardMissionDTO){
        if (rewardMissionDTO == null) {
            return ResponseEntity.badRequest().body("등록된 기존 리워드가 없습니다.");
        }

        try{
            rewardMissionWriteService.rewardMissionModify(rewardMissionDTO);
            return ResponseEntity.ok("리워드를 수정했습니다");
        } catch (IllegalArgumentException e) {
            // 예외를 직접 던지고 GlobalExceptionHandler에서 처리
            throw new IllegalArgumentException("리워드 수정에 실패했습니다: " + e.getMessage());
        } catch (Exception e) {
            // 일반적인 서버 오류 처리
            throw new RuntimeException("서버 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }

}
