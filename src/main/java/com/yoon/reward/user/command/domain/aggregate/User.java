package com.yoon.reward.user.command.domain.aggregate;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String accountHolder;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private LocalDate signDate;

    @Column
    private LocalDate resignDate;

    @Column
    private Long userPoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    public  User(){}

    public User(String userId, String userPassword, String userName, String userNickname, String userPhone,
                String accountHolder, String bankName, String accountNumber, LocalDate signDate, LocalDate resignDate, Long userPoint, UserRole userRole) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPhone = userPhone;
        this.accountHolder = accountHolder;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.signDate = signDate;
        this.resignDate = resignDate;
        this.userPoint = userPoint;
        this.userRole = userRole;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Long getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Long userPoint) {
        this.userPoint = userPoint;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", signDate=" + signDate +
                ", resignDate=" + resignDate +
                ", userPoint=" + userPoint +
                ", userRole=" + userRole +
                '}';
    }
}
