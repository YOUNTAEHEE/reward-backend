package com.yoon.reward.reward.command.application.controller;

import com.yoon.reward.reward.command.application.service.RewardMissionTransactionService;
import com.yoon.reward.reward.query.service.RewardMissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward/list")
public class RewardMissionTransactionController {
    private RewardMissionTransactionService rewardMissionTransactionService;

    public RewardMissionTransactionController(RewardMissionTransactionService rewardMissionTransactionService) {
        this.rewardMissionTransactionService = rewardMissionTransactionService;
    }


    //미션성공시 포인트 지급
    @PostMapping("/mission/{rewardNo}")
    public ResponseEntity<String> rewardMissionValidate(@PathVariable Long rewardNo, @RequestParam String userId,
                                                        @RequestParam String missionAnswer){

        if (missionAnswer == null || missionAnswer.isEmpty()) {
            return ResponseEntity.badRequest().body("정답을 입력해주세요.");
        }

        try{
            boolean isMissionSuccess = rewardMissionTransactionService.rewardMissionValidate(rewardNo,userId,missionAnswer);
            if(isMissionSuccess){
                return ResponseEntity.ok("미션 성공했습니다!");
            }else{
                return ResponseEntity.badRequest().body("미션 실패!");
            }
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
}
