package com.yoon.reward.reward.command.domain.aggregate;

import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "reward")
public class Reward {

    public Reward(){}

    public Reward(Long rewardId,  String advertiserId, String salesId, RewardStatus rewardStatus, String productURL,
                  String keyword, String advertiserChannel, Long rewardProductPrice, Long rewardPoint,
                  String productId, String optionId,  String productName, String priceComparison,  LocalDate rewardStartDate, LocalDate rewardEndDate,
                  Long inflowCount, String rewardMemo) {
        this.rewardId = rewardId;
        this.advertiserId = advertiserId;
        this.salesId = salesId;
        this.rewardStatus = rewardStatus;
        this.productURL = productURL;
        this.keyword = keyword;
        this.advertiserChannel = advertiserChannel;
        this.rewardProductPrice = rewardProductPrice;
        this.rewardPoint = rewardPoint;
        this.productId = productId;
        this.optionId = optionId;
        this.productName = productName;
        this.priceComparison = priceComparison;
        this.rewardStartDate = rewardStartDate;
        this.rewardEndDate = rewardEndDate;
        this.inflowCount = inflowCount;
        this.rewardMemo = rewardMemo;
    }
    public Reward(RewardMissionDTO rewardMissionDTO) {
//        this.rewardNo = rewardMissionDTO.getRewardNo();
        this.rewardId = rewardMissionDTO.getRewardId();
        this.advertiserId = rewardMissionDTO.getAdvertiserId();
        this.salesId = rewardMissionDTO.getSalesId();
        this.rewardStatus = rewardMissionDTO.getRewardStatus();
        this.productURL = rewardMissionDTO.getProductURL();
        this.keyword = rewardMissionDTO.getKeyword();
        this.advertiserChannel = rewardMissionDTO.getAdvertiserChannel();
        this.rewardProductPrice = rewardMissionDTO.getRewardProductPrice();
        this.rewardPoint = rewardMissionDTO.getRewardPoint();
        this.productId = rewardMissionDTO.getProductId();
        this.optionId = rewardMissionDTO.getOptionId();
        this.productName = rewardMissionDTO.getProductName();
        this.priceComparison = rewardMissionDTO.getPriceComparison();
        this.rewardStartDate = rewardMissionDTO.getRewardStartDate();
        this.rewardEndDate = rewardMissionDTO.getRewardEndDate();
        this.inflowCount = rewardMissionDTO.getInflowCount();
        this.rewardMemo = rewardMissionDTO.getRewardMemo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardNo;

    @Column(name = "reward_id", nullable = false)
    private Long rewardId;

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
    private String advertiserChannel;

    @Column(nullable = false)
    private Long rewardProductPrice;

    @Column(nullable = false)
    private Long rewardPoint;

    @Column(nullable = false)
    private String productId;

    @Column
    private String optionId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String priceComparison;

    @Column(nullable = false)
    private LocalDate rewardStartDate;

    @Column(nullable = false)
    private LocalDate rewardEndDate;

    @Column(nullable = false)
    private Long inflowCount;

    @Column
    private Long actualInflowCount;

    @Column
    private String rewardMemo;


    public void setRewardStatus(RewardStatus rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Long getActualInflowCount() {
        return actualInflowCount;
    }

    public void setActualInflowCount(Long actualInflowCount) {
        this.actualInflowCount = actualInflowCount;
    }
}
