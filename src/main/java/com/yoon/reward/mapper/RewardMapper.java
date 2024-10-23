package com.yoon.reward.mapper;

import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RewardMapper {

    RewardMissionDTO getRewardMissionById(Long rewardNo);
    Reward findRewardByNo (Long rewardNo);
    List<Map<String, Object>> findRewardAll();
}
