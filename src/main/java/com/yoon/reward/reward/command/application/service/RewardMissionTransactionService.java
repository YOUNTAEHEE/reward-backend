package com.yoon.reward.reward.command.application.service;

import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.command.application.service.UpdatePointService;
import com.yoon.reward.point.command.domain.aggregate.PointAction;
import com.yoon.reward.point.command.domain.repository.PointCommandRepository;
import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import com.yoon.reward.reward.command.domain.repository.RewardCommandRepository;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import com.yoon.reward.mapper.RewardMapper;
import com.yoon.reward.reward.query.repository.RewardQueryRepository;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;
@Service
public class RewardMissionTransactionService {

    private final UpdatePointService updatePointService;
    private final RewardMapper rewardMapper;
    private final RewardCommandRepository rewardCommandRepository;

    public RewardMissionTransactionService(UpdatePointService updatePointService, RewardMapper rewardMapper,
                                           RewardCommandRepository rewardCommandRepository) {
        this.updatePointService = updatePointService;
        this.rewardMapper = rewardMapper;
        this.rewardCommandRepository = rewardCommandRepository;
    }

    //미션성공시 포인트 지급
    public boolean rewardMissionValidate(Long rewardNo, String userId, String missionAnswer){
        RewardMissionDTO rewardMissionDTO = rewardMapper.getRewardMissionById(rewardNo);
        if(rewardMissionDTO == null){
            throw new IllegalArgumentException("해당 미션을 찾을 수 없습니다.");
        }
        if (rewardMissionDTO.getRewardStatus() == RewardStatus.INACTIVE) {
            throw new IllegalStateException("이미 끝난 미션입니다.");
        }
        if (missionAnswer == null || missionAnswer.isEmpty()) {
            throw new IllegalStateException("정답을 입력해주세요.");
        }
        if(rewardMissionDTO.getProductId() == missionAnswer){
            //만약 실 유입수가 유입수를 넘어선다면 포인트 지급 막기
            if(rewardMissionDTO.getInflowCount() > rewardMissionDTO.getActualInflowCount()){
                rewardMissionDTO.setRewardStatus(RewardStatus.INACTIVE);
                new Reward(rewardMissionDTO);
                throw new IllegalStateException("끝난 미션입니다.");
            } else{
                PointDetailDTO pointDetailDTO = new PointDetailDTO();
                pointDetailDTO.setUserId(userId);
                pointDetailDTO.setPointAction(PointAction.POINT_DEPOSIT);
                pointDetailDTO.setPointDate(now());
                pointDetailDTO.setPointDelta(rewardMissionDTO.getRewardPoint());
                updatePointService.processPointTransaction(pointDetailDTO);
                //실유입수 칼럼에 저장코드
                Reward reward = new Reward(
                        rewardMissionDTO.getRewardNo(),
                        rewardMissionDTO.getRewardId(),
                        rewardMissionDTO.getAdvertiserId(),
                        rewardMissionDTO.getSalesId(),
                        rewardMissionDTO.getRewardStatus(),
                        rewardMissionDTO.getProductURL(),
                        rewardMissionDTO.getKeyword(),
                        rewardMissionDTO.getSalesChannel(),
                        rewardMissionDTO.getRewardProductPrice(),
                        rewardMissionDTO.getRewardPoint(),
                        rewardMissionDTO.getProductId(),
                        rewardMissionDTO.getOptionId(),
                        rewardMissionDTO.getProductName(),
                        rewardMissionDTO.getPriceComparison(),
                        rewardMissionDTO.getRewardStartDate(),
                        rewardMissionDTO.getRewardEndDate(),
                        rewardMissionDTO.getInflowCount() + 1,
                        rewardMissionDTO.getRewardMemo()
                );
                rewardCommandRepository.save(reward);
            }
            return true;
        } else{
            return false;
        }
    }
}
