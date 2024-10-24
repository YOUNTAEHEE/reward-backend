package com.yoon.reward.reward.query.controller;

import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import com.yoon.reward.reward.query.service.RewardMissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reward/mission")
public class RewardMissionController {

    private RewardMissionService rewardMissionService;

    public RewardMissionController(RewardMissionService rewardMissionService) {
        this.rewardMissionService = rewardMissionService;
    }

//미션 하나에 대한 정보 가져오기
    @GetMapping("/{rewardNo}")
    public ResponseEntity<RewardMissionDTO> rewardMission(@PathVariable Long rewardNo){
//        Long rewardNo2 = Long.parseLong(rewardNo);
        RewardMissionDTO rewardMissionDTO = rewardMissionService.rewardMission(rewardNo);
        return ResponseEntity.ok(rewardMissionDTO);
    }
//미션 리스트 불러오기
    @GetMapping("/list")
    public ResponseEntity<?> rewardMissionList(){
       try {
           List<RewardMissionDTO> missions = rewardMissionService.rewardMissionList();
           if (missions.isEmpty()) {
               // 만약 리스트가 비어있다면 적절한 상태 코드를 반환
               return ResponseEntity.status(HttpStatus.NO_CONTENT)
                       .body(Collections.singletonMap("message", "리스트가 없습니다."));
           }
           return ResponseEntity.ok(missions);
       }catch(Exception e){
           // 리스트를 불러오지 못했을 때 예외 처리
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Collections.singletonMap("message", "리스트를 불러오는 중 오류가 발생했습니다: " + e.getMessage()));
       }
    }
}
