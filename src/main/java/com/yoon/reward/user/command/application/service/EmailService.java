// EmailService.java
package com.yoon.reward.user.command.application.service;

import com.yoon.reward.user.command.domain.aggregate.VerificationCode;
import com.yoon.reward.user.command.domain.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final VerificationCodeRepository verificationCodeRepository;
    @Value("${spring.mail.username}")
    private String senderEmail;
    private boolean isValidEmail(String email) {
        // Simple regular expression to validate email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    private String createCode() {
        int leftLimit = 48; // 숫자 '0'
        int rightLimit = 122; // 알파벳 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // 이메일 내용 생성
    private String createEmailContent(String code) {
        // 이메일 내용 작성 (HTML 형식으로 작성 가능)
        return "<h1>안녕하세요. 리워드입니다.</h1><p>인증코드는 <strong>" + code + "</strong> 입니다.</p>";
    }

    // 이메일 폼 생성
    private MimeMessage createEmailForm(String email) throws MessagingException {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
        String authCode = createCode();

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("안녕하세요. 인증번호입니다.");
        message.setFrom(senderEmail);
        message.setText(createEmailContent(authCode), "utf-8", "html");

        // 인증 코드와 만료 시간 저장 (예: 30분 후 만료)
        long expiryTime = System.currentTimeMillis() + 30 * 60 * 1000; // 30분

        // 기존 인증 코드 삭제
        verificationCodeRepository.deleteById(email);

        // 새로운 인증 코드 저장
        VerificationCode verificationCode = new VerificationCode(email, authCode, expiryTime);
        verificationCodeRepository.save(verificationCode);

        return message;
    }

    // 인증코드 이메일 발송
    public void sendEmail(String toEmail) throws MessagingException {
        // 이메일 폼 생성
        MimeMessage emailForm = createEmailForm(toEmail);
        // 이메일 발송
        javaMailSender.send(emailForm);
    }

    // 코드 검증
    public Boolean verifyEmailCode(String email, String code) {
        Optional<VerificationCode> optionalVerificationCode = verificationCodeRepository.findById(email);
        if (!optionalVerificationCode.isPresent()) {
            return false;
        }
        VerificationCode storedCode = optionalVerificationCode.get();
        // 만료 시간 확인
        if (System.currentTimeMillis() > storedCode.getExpiryTime()) {
            verificationCodeRepository.deleteById(email);
            return false;
        }
        // 코드 비교
        boolean isValid = storedCode.getCode().equals(code);
        if (isValid) {
            // 검증 완료 후 코드 삭제 (옵션)
            verificationCodeRepository.deleteById(email);
        }
        return isValid;
    }
}
