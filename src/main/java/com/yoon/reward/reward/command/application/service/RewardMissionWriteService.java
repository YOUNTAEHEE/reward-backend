package com.yoon.reward.reward.command.application.service;

import com.yoon.reward.mapper.RewardMapper;
import com.yoon.reward.point.command.application.service.UpdatePointService;
import com.yoon.reward.point.command.domain.repository.PointCommandRepository;
import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import com.yoon.reward.reward.command.domain.repository.RewardCommandRepository;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class RewardMissionWriteService {
    private final PointCommandRepository pointCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final UpdatePointService updatePointService;
    private final RewardMapper rewardMapper;
    private final RewardCommandRepository rewardCommandRepository;

    public RewardMissionWriteService(PointCommandRepository pointCommandRepository,
                                     UserQueryRepository userQueryRepository, UpdatePointService updatePointService,
                                     RewardMapper rewardMapper, RewardCommandRepository rewardCommandRepository) {
        this.pointCommandRepository = pointCommandRepository;
        this.userQueryRepository = userQueryRepository;
        this.updatePointService = updatePointService;
        this.rewardMapper = rewardMapper;
        this.rewardCommandRepository = rewardCommandRepository;
    }

    //미션 등록
    public void rewardMissionWrite(RewardMissionDTO rewardMissionDTO){
        Reward reward = new Reward(rewardMissionDTO);
        rewardCommandRepository.save(reward);
    }

    //미션수정
    public void rewardMissionModify(RewardMissionDTO rewardMissionDTO){
        RewardMissionDTO existingReward = rewardMapper.getRewardMissionById(rewardMissionDTO.getRewardNo());
        //비활성화 된 미션 수정 못함
        if(rewardMissionDTO.getRewardStatus() == RewardStatus.INACTIVE){
            throw new IllegalStateException("비활성화된 미션은 수정할 수 없습니다.");
        } else {
            //유입수 수정 못함
            rewardMissionDTO.setInflowCount(existingReward.getInflowCount());
            Reward reward = new Reward(rewardMissionDTO);
            rewardCommandRepository.save(reward);
        }
    }
}
