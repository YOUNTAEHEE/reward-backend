package com.yoon.reward.user.command.domain.repository;

import com.yoon.reward.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userCommandRepository")
public interface UserCommandRepository extends JpaRepository<User, Long> {

}
