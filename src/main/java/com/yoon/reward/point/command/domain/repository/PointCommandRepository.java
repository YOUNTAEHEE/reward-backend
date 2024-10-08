package com.yoon.reward.point.command.domain.repository;

import com.yoon.reward.point.command.domain.aggregate.PointDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository("pointCommandRepository")
public interface PointCommandRepository extends JpaRepository<PointDetail, Long> {
}
