package com.yoon.reward.point.command.application.controller;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.command.application.service.UpdatePointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class UpdatePointController {
    private final UpdatePointService updatePointService;

    public UpdatePointController(UpdatePointService updatePointService) {
        this.updatePointService = updatePointService;
    }

    @PostMapping()
    public ResponseEntity<Long> updateUserPoint(@RequestBody PointDetailDTO pointDetailDTO){
        try{
            Long totalPoint = updatePointService.updateUserPoint(pointDetailDTO);
            return ResponseEntity.ok(totalPoint);
        } catch(IllegalArgumentException e) {
          return ResponseEntity.badRequest().body(null);

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
