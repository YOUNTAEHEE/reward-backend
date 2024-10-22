package com.yoon.reward.reward.query.repository;

import com.yoon.reward.reward.command.domain.aggregate.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardQueryRepository extends JpaRepository<Reward, Long> {
    //    @Query("SELECT MAX(r.rewardId) FROM Reward r")
//    Long findMaxRewardCode();
    @Query(value = "SELECT MAX(reward_id) FROM reward", nativeQuery = true)
    Long findMaxRewardCode();
}
