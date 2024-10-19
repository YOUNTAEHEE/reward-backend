package com.yoon.reward.user.command.application.controller;

import com.yoon.reward.exception.CustomBusinessException;
import com.yoon.reward.user.command.application.dto.EmailDTO;
import com.yoon.reward.user.command.application.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmailService emailService;

    // 인증코드 메일 발송
    @PostMapping("/send")
    public ResponseEntity<String> mailSend(@RequestBody EmailDTO emailDto) throws MessagingException {
        emailService.sendEmail(emailDto.getEmail());
        return ResponseEntity.ok("인증코드가 발송되었습니다.");
    }

    // 인증코드 인증
    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody EmailDTO emailDto) {
        boolean isVerify = emailService.verifyEmailCode(emailDto.getEmail(), emailDto.getVerifyCode());
        if (isVerify) {
            return ResponseEntity.ok("인증이 완료되었습니다.");  // 상태코드 200과 함께 메시지 반환
        } else {
            throw new CustomBusinessException("인증 실패하셨습니다.", HttpStatus.BAD_REQUEST); // 상태코드 400과 함께 메시지 반환
        }
    }
}