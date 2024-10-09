package com.yoon.reward.point.command.application.dto;

import com.yoon.reward.point.command.domain.aggregate.PointAction;

import java.time.LocalDateTime;

public class PointDetailDTO {
    private String userId;
    private PointAction pointAction;
    private LocalDateTime pointDate;
    private Long pointDelta;

    public PointDetailDTO(){}

    public PointDetailDTO(String userId, PointAction pointAction, LocalDateTime pointDate, Long pointDelta) {
        this.userId = userId;
        this.pointAction = pointAction;
        this.pointDate = pointDate;
        this.pointDelta = pointDelta;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PointAction getPointAction() {
        return pointAction;
    }

    public void setPointAction(PointAction pointAction) {
        this.pointAction = pointAction;
    }

    public LocalDateTime getPointDate() {
        return pointDate;
    }

    public void setPointDate(LocalDateTime pointDate) {
        this.pointDate = pointDate;
    }

    public long getPointDelta() {
        return pointDelta;
    }

    public void setPointDelta(Long pointDelta) {
        this.pointDelta = pointDelta;
    }

    @Override
    public String toString() {
        return "PointDetailDTO{" +
                "userId='" + userId + '\'' +
                ", pointAction=" + pointAction +
                ", pointDate=" + pointDate +
                ", pointDelta=" + pointDelta +
                '}';
    }
}
