package com.yoon.reward.mapper;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PointMapper {
    PointDetailDTO getPointTransactionDetail(String userId);
}
