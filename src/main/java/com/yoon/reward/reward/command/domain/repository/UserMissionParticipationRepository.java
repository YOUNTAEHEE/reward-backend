package com.yoon.reward.reward.command.domain.repository;

import com.yoon.reward.reward.command.domain.aggregate.UserMissionParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userMissionParticipationRepository")
public interface UserMissionParticipationRepository extends JpaRepository<UserMissionParticipation, Long> {
}
