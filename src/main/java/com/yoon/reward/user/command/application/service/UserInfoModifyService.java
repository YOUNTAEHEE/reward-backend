package com.yoon.reward.user.command.application.service;

import com.yoon.reward.user.command.application.dto.UserInfoModifyDTO;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.command.domain.repository.UserCommandRepository;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserInfoModifyService {

    private final UserCommandRepository userCommandRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryRepository userQueryRepository;

    @Autowired
    public UserInfoModifyService(UserCommandRepository userCommandRepository,  PasswordEncoder passwordEncoder, UserQueryRepository userQueryRepository) {
        this.userCommandRepository = userCommandRepository;
        this.passwordEncoder = passwordEncoder;
        this.userQueryRepository = userQueryRepository;
    }

    public UserInfoModifyDTO modifyUserInfo(UserInfoModifyDTO userInfoModifyDTO){
        User user = userQueryRepository.findByUserId(userInfoModifyDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저 정보가 없습니다"));
        if(userInfoModifyDTO.getUserPassword() != null && !userInfoModifyDTO.getUserNickname().isEmpty()){
            user.setUserNickname(userInfoModifyDTO.getUserNickname());
        }
        if(userInfoModifyDTO.getUserPassword() != null && !userInfoModifyDTO.getUserPassword().isEmpty()){
            user.setUserPassword(passwordEncoder.encode(userInfoModifyDTO.getUserPassword()));
        }
        if(userInfoModifyDTO.getUserPhone() != null && !userInfoModifyDTO.getUserPhone().isEmpty()){
            user.setUserPhone(userInfoModifyDTO.getUserPhone());
        }

        userCommandRepository.save(user);
        UserInfoModifyDTO updateUserInfo = new UserInfoModifyDTO(user);
        return updateUserInfo;
    }
}
