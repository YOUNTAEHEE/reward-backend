package com.yoon.reward.user.query.service;

import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.query.dto.CustomUserDetails;
import com.yoon.reward.user.query.dto.UserLoginDTO;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
public class UserInfoService implements UserDetailsService {
    private final UserQueryRepository userQueryRepository;
    private final PasswordEncoder passwordEncoder;
@Autowired
    public UserInfoService(UserQueryRepository userQueryRepository,PasswordEncoder passwordEncoder ) {
        this.userQueryRepository = userQueryRepository;
        this.passwordEncoder = passwordEncoder;
    }
//스프링 시큐리티로 인해 아래 코드 필요없음
//    public boolean loginUser(UserLoginDTO userLoginDTO) {
//    Optional<User> optionalUser = userQueryRepository.findByUserId(userLoginDTO.getUserId());
//
//        if(userLoginDTO == null){
//            throw new IllegalArgumentException("로그인 정보가 입력되지 않습니다.");
//        }
//
//        if (userLoginDTO.getUserId() == null || userLoginDTO.getUserId().isEmpty()) {
//           throw new IllegalArgumentException("아이디를 입력해주세요.");
//        }
//
//        if (userLoginDTO.getUserPassword() == null || userLoginDTO.getUserPassword().isEmpty()) {
//           throw new IllegalArgumentException("비밀번호를 입력해주세요.");
//        }
//
//        if(optionalUser.isPresent()){
//            User user = optionalUser.get();
//            return passwordEncoder.matches(userLoginDTO.getUserPassword(), user.getUserPassword());
//
//        }
//    return false;
//    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> optionalUser = userQueryRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return new CustomUserDetails(user);
        }
        return null;
    }
}
