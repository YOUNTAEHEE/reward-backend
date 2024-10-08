package com.yoon.reward.point.query.controller;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.query.service.PointTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointTransactionController {
    private final PointTransactionService pointTransactionService;

    public PointTransactionController(PointTransactionService pointTransactionService) {
        this.pointTransactionService = pointTransactionService;
    }

    @GetMapping
    public ResponseEntity<PointDetailDTO> pointTransactionDetail(String userId){
        try{
            PointDetailDTO pointDetailDTO = pointTransactionService.pointTransactionDetail(userId);
            return ResponseEntity.ok(pointDetailDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
