package com.yoon.reward.reward.command.application.controller;

import com.yoon.reward.reward.command.application.service.RewardMissionWriteService;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my/reward")
public class RewardMissionWriteController {
    private final RewardMissionWriteService rewardMissionWriteService;

    public RewardMissionWriteController(RewardMissionWriteService rewardMissionWriteService) {
        this.rewardMissionWriteService = rewardMissionWriteService;
    }

    //미션 등록
    @PostMapping("/write")
    public ResponseEntity<String> rewardMissionWrite(@RequestBody RewardMissionDTO rewardMissionDTO){
        try{
            rewardMissionWriteService.rewardMissionWrite(rewardMissionDTO);
            return ResponseEntity.ok("리워드를 등록했습니다");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("리워드 등록에 실패했습니다.");
        }  catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }


    //미션수정
    @PostMapping("/modify")
    public ResponseEntity<String> rewardMissionModify(@RequestBody RewardMissionDTO rewardMissionDTO){
        try{
            rewardMissionWriteService.rewardMissionModify(rewardMissionDTO);
            return ResponseEntity.ok("리워드를 수정했습니다");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("리워드 수정에 실패했습니다.");
        }  catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }

}
