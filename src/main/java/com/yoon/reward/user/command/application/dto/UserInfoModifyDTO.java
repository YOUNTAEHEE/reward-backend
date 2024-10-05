package com.yoon.reward.user.command.application.dto;

import com.yoon.reward.user.command.domain.aggregate.User;

public class UserInfoModifyDTO {
    private String userId;
    private String userPassword;
    private String userNickname;
    private String userPhone;

    public UserInfoModifyDTO(){}

    public UserInfoModifyDTO(String userId, String userPassword, String userNickname, String userPhone) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userPhone = userPhone;
    }

    public UserInfoModifyDTO(User user){
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userNickname = user.getUserNickname();
        this.userPhone = user.getUserPhone();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "UserInfoModifyDTO{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
