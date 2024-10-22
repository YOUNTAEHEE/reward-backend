package com.yoon.reward.reward.command.domain.repository;

import com.yoon.reward.reward.command.domain.aggregate.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("rewardCommandRepository")
public interface RewardCommandRepository extends JpaRepository<Reward, Long> {


}
