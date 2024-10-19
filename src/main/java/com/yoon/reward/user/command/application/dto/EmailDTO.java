package com.yoon.reward.user.command.application.dto;

import lombok.Data;

@Data
public class EmailDTO {

    // 이메일 주소
    private String email;
    // 인증 코드
    private String verifyCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}