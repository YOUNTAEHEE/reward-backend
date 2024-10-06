package com.yoon.reward.point.command.application.service;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.command.domain.aggregate.PointAction;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.repository.UserCommandRepository;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdatePointService {

    private UserQueryRepository userQueryRepository;
    private UserCommandRepository userCommandRepository;

    public UpdatePointService(UserQueryRepository userQueryRepository, UserCommandRepository userCommandRepository) {
        this.userQueryRepository = userQueryRepository;
        this.userCommandRepository = userCommandRepository;
    }

    public Long updateUserPoint(PointDetailDTO pointDetailDTO){
        User user = userQueryRepository.findByUserId(pointDetailDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저 정보가 없습니다."));

        if(pointDetailDTO.getPointAction() == PointAction.POINT_DEPOSIT){
            user.setUserPoint(user.getUserPoint() + pointDetailDTO.getPointDelta());
        }

        if(pointDetailDTO.getPointAction() == PointAction.POINT_WITHDRAW){
            user.setUserPoint(user.getUserPoint() - pointDetailDTO.getPointDelta());
        }

        userCommandRepository.save(user);
        Long userPoint = user.getUserPoint();
        return userPoint;
    }
}
