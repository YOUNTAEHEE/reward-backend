package com.yoon.reward.user.command.domain.repository;

import com.yoon.reward.user.command.domain.aggregate.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {
}