package com.yoon.reward.reward.query.controller;

import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import com.yoon.reward.reward.query.service.RewardMissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward/list")
public class RewardMissionController {

    private RewardMissionService rewardMissionService;

    public RewardMissionController(RewardMissionService rewardMissionService) {
        this.rewardMissionService = rewardMissionService;
    }

//미션 정보 가져오기
    @GetMapping("/mission/{rewardNo}")
    public ResponseEntity<RewardMissionDTO> rewardMission(@PathVariable Long rewardNo){
        RewardMissionDTO rewardMissionDTO = rewardMissionService.rewardMission(rewardNo);
        return ResponseEntity.ok(rewardMissionDTO);
    }


}
