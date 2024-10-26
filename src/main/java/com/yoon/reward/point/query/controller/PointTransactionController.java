package com.yoon.reward.point.query.controller;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.query.service.PointTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/my")
public class PointTransactionController {
    private final PointTransactionService pointTransactionService;

    public PointTransactionController(PointTransactionService pointTransactionService) {
        this.pointTransactionService = pointTransactionService;
    }
    //입출금 내역 조회(생각해보니까 이거 리스트로 반환아님?)
    @PostMapping("/point/detail")
    public ResponseEntity<?> pointTransactionDetail(@RequestBody Map<String, String> requestBody){
        String userId = requestBody.get("userId");
        try{
            List<PointDetailDTO> pointDetailDTO = pointTransactionService.pointTransactionDetail(userId);
            return ResponseEntity.ok(pointDetailDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //유저 테이블 총 포인트 조회
    @PostMapping("/point")
    public ResponseEntity<?> getTotalUserPoint(@RequestBody Map<String, String> requestBody){
        String userId = requestBody.get("userId");
        try{
            Map<String, Object> userPointInfo = pointTransactionService.getTotalUserPoint(userId);
            return ResponseEntity.ok(userPointInfo);
        } catch(IllegalArgumentException e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "유저 정보를 찾을 수 없습니다.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

}
