package com.yoon.reward.mapper;

import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RewardMapper {

    RewardMissionDTO getRewardMissionById(long rewardNo);

}
