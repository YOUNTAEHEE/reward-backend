package com.yoon.reward.reward.command.domain.aggregate;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reward")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rewardNo;

    @Column(nullable = false)
    private String advertiserId;

    @Column(nullable = false)
    private String salesId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RewardStatus rewardStatus;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private String salesChannel;

    @Column(nullable = false)
    private Long rewardProductPrice;

    @Column(nullable = false)
    private String rewardPoint;

    @Column(nullable = false)
    private Long productCode;

    @Column(nullable = false)
    private LocalDateTime rewardStartDate;

    @Column(nullable = false)
    private LocalDateTime rewardEndDate;

    @Column
    private Long inflowCount;

    @Column
    private Long actualInflowCount;

    @Column
    private String rewardMemo;

}
