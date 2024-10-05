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
import java.util.Set;

@Service
@Transactional
public class UserRegistService {

    private final UserCommandRepository userCommandRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryRepository userQueryRepository;
    @Autowired
    public UserRegistService(UserCommandRepository userCommandRepository,  PasswordEncoder passwordEncoder,
                             UserQueryRepository userQueryRepository) {
        this.userCommandRepository = userCommandRepository;
        this.passwordEncoder = passwordEncoder;
        this.userQueryRepository = userQueryRepository;
    }

    public void registUser(UserRegistDTO userRegistRequestDTO) {
        UserRole roles = UserRole.ROLE_USER;

        String encodedPassword = passwordEncoder.encode(userRegistRequestDTO.getUserPassword());

        User user = new User(userRegistRequestDTO.getUserId(), encodedPassword,
                userRegistRequestDTO.getUserName(), userRegistRequestDTO.getUserNickname(),
                userRegistRequestDTO.getUserPhone(), userRegistRequestDTO.getAccountHolder(),
                userRegistRequestDTO.getBankName(), userRegistRequestDTO.getAccountNumber(), 0L, roles
                );
        userCommandRepository.save(user);
    }


}
