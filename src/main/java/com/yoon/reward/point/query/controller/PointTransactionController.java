package com.yoon.reward.point.query.controller;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.query.service.PointTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/my")
public class PointTransactionController {
    private final PointTransactionService pointTransactionService;

    public PointTransactionController(PointTransactionService pointTransactionService) {
        this.pointTransactionService = pointTransactionService;
    }
    //입출금 내역 조회
    @GetMapping("/point/detail")
    public ResponseEntity<PointDetailDTO> pointTransactionDetail(String userId){
        try{
            PointDetailDTO pointDetailDTO = pointTransactionService.pointTransactionDetail(userId);
            return ResponseEntity.ok(pointDetailDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //유저 테이블 총 포인트 조회
    @GetMapping()
    public ResponseEntity<Map<String, Object>> getTotalUserPoint(@RequestParam String userId){
        try{
            Map<String, Object> userPointInfo = pointTransactionService.getTotalUserPoint(userId);
            return ResponseEntity.ok(userPointInfo);
        } catch(IllegalArgumentException e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "유저 정보를 찾을 수 없습니다.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

}
