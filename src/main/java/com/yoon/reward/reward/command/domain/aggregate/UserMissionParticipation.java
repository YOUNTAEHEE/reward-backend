package com.yoon.reward.reward.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class UserMissionParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private Long rewardNo;

    public UserMissionParticipation() {
    }

    public UserMissionParticipation(String userId, Long rewardNo) {
        this.userId = userId;
        this.rewardNo = rewardNo;
    }
}
