package com.yoon.reward.user.command.application.dto;

import com.yoon.reward.user.command.domain.aggregate.User;

public class UserInfoModifyDTO {
    private String userId;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userPhone;
    private String accountHolder;
    private String bankName;
    private String accountNumber;

    public UserInfoModifyDTO(){}

    public UserInfoModifyDTO(String userId, String userPassword, String userName, String userNickname, String userPhone,
                             String accountHolder, String bankName, String accountNumber) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPhone = userPhone;
        this.accountHolder = accountHolder;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    public UserInfoModifyDTO(User user) {
        this.userId = user.getUserId();
//        this.userPassword = user.getUserPassword();
        this.userName = user.getUserName();
        this.userNickname = user.getUserNickname();
        this.userPhone = user.getUserPhone();
        this.accountHolder = user.getAccountHolder();
        this.bankName = user.getBankName();
        this.accountNumber = user.getAccountNumber();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "UserInfoModifyDTO{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
