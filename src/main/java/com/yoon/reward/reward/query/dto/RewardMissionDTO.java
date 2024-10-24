package com.yoon.reward.reward.query.dto;

import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RewardMissionDTO {
    private Long rewardNo;
    private Long rewardId;
    private String advertiserId;
    private String salesId;
    private RewardStatus rewardStatus;
    private String productURL;
    private String keyword;
    private String advertiserChannel;
    private Long rewardProductPrice;
    private Long rewardPoint;
    private String productId;
    private String optionId;
    private String productName;
    private String priceComparison;
    private LocalDate rewardStartDate;
    private LocalDate rewardEndDate;
    private Long inflowCount;
    private Long actualInflowCount;
    private String rewardMemo;

    public RewardMissionDTO(){}

    public RewardMissionDTO(Long rewardId, String advertiserId, String salesId, RewardStatus rewardStatus,
                            String productURL, String keyword, String advertiserChannel, Long rewardProductPrice, Long rewardPoint,
                            String productId, String optionId, String productName, String priceComparison, LocalDate rewardStartDate,
                            LocalDate rewardEndDate, Long inflowCount, Long actualInflowCount, String rewardMemo) {

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
        this.actualInflowCount = actualInflowCount;
        this.rewardMemo = rewardMemo;
    }

    public RewardMissionDTO(Long rewardNo, Long rewardPoint) {
        this.rewardNo = rewardNo;
        this.rewardPoint = rewardPoint;
    }

    public RewardMissionDTO(Long rewardPoint, String keyword, String advertiserChannel, String productName ,Long rewardProductPrice) {
        this.rewardPoint = rewardPoint;
        this.keyword = keyword;
        this.advertiserChannel = advertiserChannel;
        this.productName = productName;
        this.rewardProductPrice = rewardProductPrice;
    }
//    public RewardMissionDTO(Reward reward) {
//        this.rewardNo = reward.getRewardNo();
//        this.rewardId = reward.getRewardId();
//        this.advertiserId = reward.getAdvertiserId();
//        this.salesId = salesId;
//        this.rewardStatus = rewardStatus;
//        this.productURL = productURL;
//        this.keyword = keyword;
//        this.salesChannel = salesChannel;
//        this.rewardProductPrice = rewardProductPrice;
//        this.rewardPoint = rewardPoint;
//        this.productId = productId;
//        this.optionId = optionId;
//        this.productName = productName;
//        this.priceComparison = priceComparison;
//        this.rewardStartDate = rewardStartDate;
//        this.rewardEndDate = rewardEndDate;
//        this.inflowCount = inflowCount;
//        this.actualInflowCount = actualInflowCount;
//        this.rewardMemo = rewardMemo;
//    }

//    public RewardMissionDTO(String advertiserId, String salesId, RewardStatus rewardStatus, String keyword, String productURL,
//                            String salesChannel, Long rewardProductPrice, Long rewardPoint, String productId, String optionId,  String productName, String priceComparison,
//                            LocalDate rewardStartDate, LocalDate rewardEndDate,
//                            Long inflowCount, String rewardMemo) {
//        this.advertiserId = advertiserId;
//        this.salesId = salesId;
//        this.rewardStatus = rewardStatus;
//        this.productURL = productURL;
//        this.keyword = keyword;
//        this.salesChannel = salesChannel;
//        this.rewardProductPrice = rewardProductPrice;
//        this.rewardPoint = rewardPoint;
//        this.productId = productId;
//        this.optionId = optionId;
//        this.productName = productName;
//        this.priceComparison = priceComparison;
//        this.rewardStartDate = rewardStartDate;
//        this.rewardEndDate = rewardEndDate;
//        this.inflowCount = inflowCount;
//        this.rewardMemo = rewardMemo;
//    }
//
//    public RewardMissionDTO(Long rewardNo,Long rewardId,  String advertiserId, String salesId, RewardStatus rewardStatus, String productURL, String keyword,
//                            String salesChannel, Long rewardProductPrice, Long rewardPoint,String productId, String optionId,  String productName, String priceComparison,
//                            LocalDate rewardStartDate, LocalDate rewardEndDate, Long inflowCount,
//                            Long actualInflowCount, String rewardMemo) {
//        this.rewardNo = rewardNo;
//        this.rewardId = rewardId;
//        this.advertiserId = advertiserId;
//        this.salesId = salesId;
//        this.rewardStatus = rewardStatus;
//        this.productURL = productURL;
//        this.keyword = keyword;
//        this.salesChannel = salesChannel;
//        this.rewardProductPrice = rewardProductPrice;
//        this.rewardPoint = rewardPoint;
//        this.productId = productId;
//        this.optionId = optionId;
//        this.productName = productName;
//        this.priceComparison = priceComparison;
//        this.rewardStartDate = rewardStartDate;
//        this.rewardEndDate = rewardEndDate;
//        this.inflowCount = inflowCount;
//        this.actualInflowCount = actualInflowCount;
//        this.rewardMemo = rewardMemo;
//    }


    @Override
    public String toString() {
        return "RewardMissionDTO{" +
                "rewardNo=" + rewardNo +
                ", rewardId=" + rewardId +
                ", advertiserId='" + advertiserId + '\'' +
                ", salesId='" + salesId + '\'' +
                ", rewardStatus=" + rewardStatus +
                ", productURL='" + productURL + '\'' +
                ", keyword='" + keyword + '\'' +
                ", advertiserChannel='" + advertiserChannel + '\'' +
                ", rewardProductPrice=" + rewardProductPrice +
                ", rewardPoint=" + rewardPoint +
                ", productId='" + productId + '\'' +
                ", optionId='" + optionId + '\'' +
                ", productName='" + productName + '\'' +
                ", priceComparison='" + priceComparison + '\'' +
                ", rewardStartDate=" + rewardStartDate +
                ", rewardEndDate=" + rewardEndDate +
                ", inflowCount=" + inflowCount +
                ", actualInflowCount=" + actualInflowCount +
                ", rewardMemo='" + rewardMemo + '\'' +
                '}';
    }
}
