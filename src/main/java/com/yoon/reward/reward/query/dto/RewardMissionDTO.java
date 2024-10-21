package com.yoon.reward.reward.query.dto;

import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class RewardMissionDTO {
    private Long rewardNo;
    private String advertiserId;
    private String salesId;
    private RewardStatus rewardStatus;
    private String productURL;
    private String keyword;
    private String salesChannel;
    private Long rewardProductPrice;
    private Long rewardPoint;
    private String productCode;
    private String productName;
    private LocalDate rewardStartDate;
    private LocalDate rewardEndDate;
    private Long inflowCount;
    private Long actualInflowCount;
    private String rewardMemo;

    public RewardMissionDTO(){}

    public RewardMissionDTO(Long rewardNo, String advertiserId, String salesId, RewardStatus rewardStatus,
                            String productURL, String keyword, String salesChannel, Long rewardProductPrice, Long rewardPoint,
                            String productName , String productCode, LocalDate rewardStartDate, LocalDate rewardEndDate,
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

    public RewardMissionDTO(String advertiserId, String salesId, RewardStatus rewardStatus, String keyword, String productURL,
                            String salesChannel, Long rewardProductPrice, Long rewardPoint, String productName, String productCode,
                            LocalDate rewardStartDate, LocalDate rewardEndDate,
                            Long inflowCount, String rewardMemo) {
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

    public RewardMissionDTO(Long rewardNo, String advertiserId, String salesId, RewardStatus rewardStatus, String productURL, String keyword,
                            String salesChannel, Long rewardProductPrice, Long rewardPoint, String productCode,String productName,
                            LocalDate rewardStartDate, LocalDate rewardEndDate, Long inflowCount,
                            Long actualInflowCount, String rewardMemo) {
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
        this.actualInflowCount = actualInflowCount;
        this.rewardMemo = rewardMemo;
    }

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

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    @Override
    public String toString() {
        return "RewardMissionDTO{" +
                "rewardNo=" + rewardNo +
                ", advertiserId='" + advertiserId + '\'' +
                ", salesId='" + salesId + '\'' +
                ", rewardStatus=" + rewardStatus +
                ", productURL='" + productURL + '\'' +
                ", keyword='" + keyword + '\'' +
                ", salesChannel='" + salesChannel + '\'' +
                ", rewardProductPrice=" + rewardProductPrice +
                ", rewardPoint=" + rewardPoint +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", rewardStartDate=" + rewardStartDate +
                ", rewardEndDate=" + rewardEndDate +
                ", inflowCount=" + inflowCount +
                ", actualInflowCount=" + actualInflowCount +
                ", rewardMemo='" + rewardMemo + '\'' +
                '}';
    }
}
