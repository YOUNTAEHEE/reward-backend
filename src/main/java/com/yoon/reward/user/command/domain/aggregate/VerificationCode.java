package com.yoon.reward.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "verification_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode {

    @Id
    private String email;

    private String code;

    private long expiryTime; // 만료 시간 (Epoch Milliseconds)
}

