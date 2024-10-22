package com.yoon.reward.point.command.application.service;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.command.domain.aggregate.PointAction;
import com.yoon.reward.point.command.domain.aggregate.PointDetail;
import com.yoon.reward.point.command.domain.repository.PointCommandRepository;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.repository.UserCommandRepository;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UpdatePointService {

    private UserQueryRepository userQueryRepository;
    private UserCommandRepository userCommandRepository;
    private PointCommandRepository pointCommandRepository;

    public UpdatePointService(UserQueryRepository userQueryRepository, UserCommandRepository userCommandRepository,
                              PointCommandRepository pointCommandRepository) {
        this.userQueryRepository = userQueryRepository;
        this.userCommandRepository = userCommandRepository;
        this.pointCommandRepository = pointCommandRepository;
    }


//입출금, 유저 테이블에도 총 포인트 저장
    public void processPointTransaction(PointDetailDTO pointDetailDTO){
        if(pointDetailDTO == null){
            throw new IllegalArgumentException("포인트가 반영이 되지 않습니다.");
        }

        User user = userQueryRepository.findByUserId(pointDetailDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저 정보가 없습니다."));

        PointDetail pointDetail = new PointDetail(pointDetailDTO);
        pointCommandRepository.save(pointDetail);

        if(pointDetailDTO.getPointAction() == PointAction.POINT_DEPOSIT){
            user.setUserPoint(user.getUserPoint() + pointDetailDTO.getPointDelta());
        }

        if(pointDetailDTO.getPointAction() == PointAction.POINT_WITHDRAW){
            user.setUserPoint(user.getUserPoint() - pointDetailDTO.getPointDelta());
        }
        userCommandRepository.save(user);
    }

}
