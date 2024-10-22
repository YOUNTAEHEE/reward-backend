package com.yoon.reward.mapper;

import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RewardMapper {

    RewardMissionDTO getRewardMissionById(Long rewardNo);
    Reward findRewardByNo (Long rewardNo);
}
