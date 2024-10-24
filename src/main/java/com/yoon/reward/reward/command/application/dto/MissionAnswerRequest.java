package com.yoon.reward.reward.command.application.dto;

import lombok.Getter;
import lombok.Setter;

public class MissionAnswerRequest {
    private String userId;
    private String missionAnswer;

    public MissionAnswerRequest(){}

    public MissionAnswerRequest(String userId, String missionAnswer) {
        this.userId = userId;
        this.missionAnswer = missionAnswer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMissionAnswer() {
        return missionAnswer;
    }

    public void setMissionAnswer(String missionAnswer) {
        this.missionAnswer = missionAnswer;
    }
}
