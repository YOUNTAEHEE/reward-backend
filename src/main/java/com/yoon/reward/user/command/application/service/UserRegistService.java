package com.yoon.reward.user.command.application.service;

import com.yoon.reward.user.command.application.dto.UserInfoModifyDTO;
import com.yoon.reward.user.command.application.dto.UserRegistDTO;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.aggregate.UserRole;
import com.yoon.reward.user.command.domain.repository.UserCommandRepository;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.time.LocalDate.now;

@Service
@Transactional
public class UserRegistService {

    private final UserCommandRepository userCommandRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryRepository userQueryRepository;

    public UserRegistService(UserCommandRepository userCommandRepository,  PasswordEncoder passwordEncoder,
                             UserQueryRepository userQueryRepository) {
        this.userCommandRepository = userCommandRepository;
        this.passwordEncoder = passwordEncoder;
        this.userQueryRepository = userQueryRepository;
    }

    public void registUser(UserRegistDTO userRegistRequestDTO) {
        UserRole roles = UserRole.ROLE_USER;

        Optional<User> optionalUser = userQueryRepository.findByUserId(userRegistRequestDTO.getUserId());
        if(optionalUser.isPresent()){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        if(userRegistRequestDTO == null){
            throw new IllegalArgumentException("가입 정보가 입력이 되지 않습니다.");
        }
        // 필수 입력값이 null일 경우 예외 처리
        if (userRegistRequestDTO.getUserId() == null || userRegistRequestDTO.getUserId().isEmpty()) {
            throw new IllegalArgumentException("아이디는 필수 항목입니다.");
        }

        if (userRegistRequestDTO.getUserPassword() == null || userRegistRequestDTO.getUserPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수 항목입니다.");
        }

        if (userRegistRequestDTO.getUserName() == null || userRegistRequestDTO.getUserName().isEmpty()) {
            throw new IllegalArgumentException("이름은 필수 항목입니다.");
        }

        if (userRegistRequestDTO.getUserNickname() == null || userRegistRequestDTO.getUserNickname().isEmpty()) {
            throw new IllegalArgumentException("닉네임은 필수 항목입니다.");
        }

        if (userRegistRequestDTO.getUserPhone() == null || userRegistRequestDTO.getUserPhone().isEmpty()) {
            throw new IllegalArgumentException("전화번호는 필수 항목입니다.");
        }

        if (userRegistRequestDTO.getAccountHolder() == null || userRegistRequestDTO.getAccountHolder().isEmpty()) {
            throw new IllegalArgumentException("계좌 소유자는 필수 항목입니다.");
        }

        if (userRegistRequestDTO.getBankName() == null || userRegistRequestDTO.getBankName().isEmpty()) {
            throw new IllegalArgumentException("은행명은 필수 항목입니다.");
        }

        if (userRegistRequestDTO.getAccountNumber() == null || userRegistRequestDTO.getAccountNumber().isEmpty()) {
            throw new IllegalArgumentException("계좌 번호는 필수 항목입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userRegistRequestDTO.getUserPassword());

        User user = new User(userRegistRequestDTO.getUserId(), encodedPassword,
                userRegistRequestDTO.getUserName(), userRegistRequestDTO.getUserNickname(),
                userRegistRequestDTO.getUserPhone(), userRegistRequestDTO.getAccountHolder(),
                userRegistRequestDTO.getBankName(), userRegistRequestDTO.getAccountNumber(), now(),null, 0L, roles
                );
        userCommandRepository.save(user);
    }
}
