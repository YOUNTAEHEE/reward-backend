package com.yoon.reward.reward.query.dto;

import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class RewardMissionDTO {
    private long rewardNo;
    private String advertiserId;
    private String salesId;
    private RewardStatus rewardStatus;
    private String keyword;
    private String salesChannel;
    private long rewardProductPrice;
    private long rewardPoint;
    private String productCode;
    private LocalDateTime rewardStartDate;
    private LocalDateTime rewardEndDate;
    private Long inflowCount;
    private Long actualInflowCount;
    private String rewardMemo;

    public RewardMissionDTO(){}

    public RewardMissionDTO(long rewardNo, String advertiserId, String salesId, RewardStatus rewardStatus,
                            String keyword, String salesChannel, long rewardProductPrice, long rewardPoint,
                            String productCode, LocalDateTime rewardStartDate, LocalDateTime rewardEndDate,
                            Long inflowCount, String rewardMemo) {
        this.rewardNo = rewardNo;
        this.advertiserId = advertiserId;
        this.salesId = salesId;
        this.rewardStatus = rewardStatus;
        this.keyword = keyword;
        this.salesChannel = salesChannel;
        this.rewardProductPrice = rewardProductPrice;
        this.rewardPoint = rewardPoint;
        this.productCode = productCode;
        this.rewardStartDate = rewardStartDate;
        this.rewardEndDate = rewardEndDate;
        this.inflowCount = inflowCount;
        this.rewardMemo = rewardMemo;
    }

    public RewardMissionDTO(String advertiserId, String salesId, RewardStatus rewardStatus, String keyword,
                            String salesChannel, long rewardProductPrice, long rewardPoint, String productCode,
                            LocalDateTime rewardStartDate, LocalDateTime rewardEndDate,
                            Long inflowCount, String rewardMemo) {
        this.advertiserId = advertiserId;
        this.salesId = salesId;
        this.rewardStatus = rewardStatus;
        this.keyword = keyword;
        this.salesChannel = salesChannel;
        this.rewardProductPrice = rewardProductPrice;
        this.rewardPoint = rewardPoint;
        this.productCode = productCode;
        this.rewardStartDate = rewardStartDate;
        this.rewardEndDate = rewardEndDate;
        this.inflowCount = inflowCount;
        this.rewardMemo = rewardMemo;
    }

    public RewardMissionDTO(long rewardNo, String advertiserId, String salesId, RewardStatus rewardStatus, String keyword,
                            String salesChannel, long rewardProductPrice, long rewardPoint, String productCode,
                            LocalDateTime rewardStartDate, LocalDateTime rewardEndDate, Long inflowCount,
                            Long actualInflowCount, String rewardMemo) {
        this.rewardNo = rewardNo;
        this.advertiserId = advertiserId;
        this.salesId = salesId;
        this.rewardStatus = rewardStatus;
        this.keyword = keyword;
        this.salesChannel = salesChannel;
        this.rewardProductPrice = rewardProductPrice;
        this.rewardPoint = rewardPoint;
        this.productCode = productCode;
        this.rewardStartDate = rewardStartDate;
        this.rewardEndDate = rewardEndDate;
        this.inflowCount = inflowCount;
        this.actualInflowCount = actualInflowCount;
        this.rewardMemo = rewardMemo;
    }

    public long getRewardNo() {
        return rewardNo;
    }

    public void setRewardNo(long rewardNo) {
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

    public long getRewardProductPrice() {
        return rewardProductPrice;
    }

    public void setRewardProductPrice(long rewardProductPrice) {
        this.rewardProductPrice = rewardProductPrice;
    }

    public long getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(long rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public LocalDateTime getRewardStartDate() {
        return rewardStartDate;
    }

    public void setRewardStartDate(LocalDateTime rewardStartDate) {
        this.rewardStartDate = rewardStartDate;
    }

    public LocalDateTime getRewardEndDate() {
        return rewardEndDate;
    }

    public void setRewardEndDate(LocalDateTime rewardEndDate) {
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

    @Override
    public String toString() {
        return "RewardMissionDTO{" +
                "rewardNo=" + rewardNo +
                ", advertiserId='" + advertiserId + '\'' +
                ", salesId='" + salesId + '\'' +
                ", rewardStatus=" + rewardStatus +
                ", keyword='" + keyword + '\'' +
                ", salesChannel='" + salesChannel + '\'' +
                ", rewardProductPrice=" + rewardProductPrice +
                ", rewardPoint=" + rewardPoint +
                ", productCode='" + productCode + '\'' +
                ", rewardStartDate=" + rewardStartDate +
                ", rewardEndDate=" + rewardEndDate +
                ", inflowCount=" + inflowCount +
                ", actualInflowCount=" + actualInflowCount +
                ", rewardMemo='" + rewardMemo + '\'' +
                '}';
    }
}
