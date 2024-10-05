package com.yoon.reward.user.command.domain.repository;

import com.yoon.reward.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommandRepository extends JpaRepository<User, Long> {

}
