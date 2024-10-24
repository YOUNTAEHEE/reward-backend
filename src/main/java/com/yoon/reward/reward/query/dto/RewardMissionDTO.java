package com.yoon.reward.reward.query.dto;

import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import jakarta.persistence.Column;

import java.time.LocalDate;

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

    public Long getRewardNo() {
        return rewardNo;
    }

    public void setRewardNo(Long rewardNo) {
        this.rewardNo = rewardNo;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public RewardStatus getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(RewardStatus rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAdvertiserChannel() {
        return advertiserChannel;
    }

    public void setAdvertiserChannel(String advertiserChannel) {
        this.advertiserChannel = advertiserChannel;
    }

    public Long getRewardProductPrice() {
        return rewardProductPrice;
    }

    public void setRewardProductPrice(Long rewardProductPrice) {
        this.rewardProductPrice = rewardProductPrice;
    }

    public Long getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(Long rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public LocalDate getRewardStartDate() {
        return rewardStartDate;
    }

    public void setRewardStartDate(LocalDate rewardStartDate) {
        this.rewardStartDate = rewardStartDate;
    }

    public LocalDate getRewardEndDate() {
        return rewardEndDate;
    }

    public void setRewardEndDate(LocalDate rewardEndDate) {
        this.rewardEndDate = rewardEndDate;
    }

    public Long getInflowCount() {
        return inflowCount;
    }

    public void setInflowCount(Long inflowCount) {
        this.inflowCount = inflowCount;
    }

    public String getRewardMemo() {
        return rewardMemo;
    }

    public void setRewardMemo(String rewardMemo) {
        this.rewardMemo = rewardMemo;
    }

    public Long getActualInflowCount() {
        return actualInflowCount;
    }

    public void setActualInflowCount(Long actualInflowCount) {
        this.actualInflowCount = actualInflowCount;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getPriceComparison() {
        return priceComparison;
    }

    public void setPriceComparison(String priceComparison) {
        this.priceComparison = priceComparison;
    }

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
