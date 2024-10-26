package com.yoon.reward.mapper;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointMapper {
    List<PointDetailDTO> getPointTransactionDetail(String userId);
}
