package com.yoon.reward.point.command.domain.aggregate;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.user.command.domain.aggregate.UserRole;
import jakarta.persistence.*;
import lombok.Cleanup;

import java.time.LocalDateTime;
@Entity
@Table(name = "point_detail")
public class PointDetail {
    public PointDetail(){}
    public PointDetail(PointDetailDTO pointDetailDTO){
        this.userId = pointDetailDTO.getUserId();
        this.pointAction = pointDetailDTO.getPointAction();
        this.pointDate = pointDetailDTO.getPointDate();
        this.pointDelta = pointDetailDTO.getPointDelta();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointNo;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointAction pointAction;

    @Column(nullable = false)
    private LocalDateTime pointDate;

    @Column(nullable = false)
    private Long pointDelta;


}
