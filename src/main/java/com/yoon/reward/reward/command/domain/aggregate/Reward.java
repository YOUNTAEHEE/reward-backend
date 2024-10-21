package com.yoon.reward.reward.command.domain.aggregate;

import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reward")
public class Reward {

    public Reward(){}

    public Reward(Long rewardNo, String advertiserId, String salesId, RewardStatus rewardStatus, String productURL,
                  String keyword, String salesChannel, Long rewardProductPrice, Long rewardPoint,
                  String productCode, String productName, LocalDate rewardStartDate, LocalDate rewardEndDate,
                  Long inflowCount, String rewardMemo) {
        this.rewardNo = rewardNo;
        this.advertiserId = advertiserId;
        this.salesId = salesId;
        this.rewardStatus = rewardStatus;
        this.productURL = productURL;
        this.keyword = keyword;
        this.salesChannel = salesChannel;
        this.rewardProductPrice = rewardProductPrice;
        this.rewardPoint = rewardPoint;
        this.productCode = productCode;
        this.productName = productName;
        this.rewardStartDate = rewardStartDate;
        this.rewardEndDate = rewardEndDate;
        this.inflowCount = inflowCount;
        this.rewardMemo = rewardMemo;
    }
    public Reward(RewardMissionDTO rewardMissionDTO) {
        this.rewardNo = rewardMissionDTO.getRewardNo();
        this.advertiserId = rewardMissionDTO.getAdvertiserId();
        this.salesId = rewardMissionDTO.getSalesId();
        this.rewardStatus = rewardMissionDTO.getRewardStatus();
        this.productURL = rewardMissionDTO.getProductURL();
        this.keyword = rewardMissionDTO.getKeyword();
        this.salesChannel = rewardMissionDTO.getSalesChannel();
        this.rewardProductPrice = rewardMissionDTO.getRewardProductPrice();
        this.rewardPoint = rewardMissionDTO.getRewardPoint();
        this.productCode = rewardMissionDTO.getProductCode();
        this.productName = rewardMissionDTO.getProductName();
        this.rewardStartDate = rewardMissionDTO.getRewardStartDate();
        this.rewardEndDate = rewardMissionDTO.getRewardEndDate();
        this.inflowCount = rewardMissionDTO.getInflowCount();
        this.rewardMemo = rewardMissionDTO.getRewardMemo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardNo;

    @Column(nullable = false)
    private String advertiserId;

    @Column(nullable = false)
    private String salesId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RewardStatus rewardStatus;

    @Column(nullable = false)
    private String productURL;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private String salesChannel;

    @Column(nullable = false)
    private Long rewardProductPrice;

    @Column(nullable = false)
    private Long rewardPoint;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private LocalDate rewardStartDate;

    @Column(nullable = false)
    private LocalDate rewardEndDate;

    @Column
    private Long inflowCount;

    @Column
    private Long actualInflowCount;

    @Column
    private String rewardMemo;

}
