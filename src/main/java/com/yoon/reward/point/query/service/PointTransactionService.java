package com.yoon.reward.point.query.service;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.command.domain.repository.PointCommandRepository;
import com.yoon.reward.mapper.PointMapper;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.repository.UserCommandRepository;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PointTransactionService {
    private UserQueryRepository userQueryRepository;
    private UserCommandRepository userCommandRepository;
    private PointCommandRepository pointCommandRepository;
    private PointMapper pointMapper;
    @Autowired
    public PointTransactionService(UserQueryRepository userQueryRepository, UserCommandRepository userCommandRepository,
                                   PointCommandRepository pointCommandRepository, PointMapper pointMapper) {
        this.userQueryRepository = userQueryRepository;
        this.userCommandRepository = userCommandRepository;
        this.pointCommandRepository = pointCommandRepository;
        this.pointMapper = pointMapper;
    }

    //입출금 내역 조회
    public PointDetailDTO pointTransactionDetail(String userId){
        PointDetailDTO pointDetail = pointMapper.getPointTransactionDetail(userId);
        if (pointDetail == null) {
            throw new IllegalArgumentException("입출금 내역이 존재하지 않습니다.");
        }
        return pointDetail;
    }

    //유저 테이블 총 포인트 조회
    public Map<String, Object> getTotalUserPoint(String userId){
        User user = userQueryRepository.findByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("유저 정보가 없습니다."));
        Map<String, Object> response = new HashMap<>();
        response.put("userNickname", user.getUserNickname());
        response.put("userPoint", user.getUserPoint());
        return response;
    }
}
