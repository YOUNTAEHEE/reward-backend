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

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> optionalUser = userQueryRepository.findByUserId(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return new CustomUserDetails(user);
        }
        return null;
    }
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
