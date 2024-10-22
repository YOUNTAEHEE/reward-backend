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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;
@Service
@Transactional
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
        Reward reward = rewardMapper.findRewardByNo(rewardNo);
        if(reward == null){
            throw new IllegalArgumentException("해당 미션을 찾을 수 없습니다.");
        }
        if (reward.getRewardStatus() == RewardStatus.INACTIVE) {
            throw new IllegalStateException("이미 끝난 미션입니다.");
        }
        if (missionAnswer == null || missionAnswer.isEmpty()) {
            throw new IllegalStateException("정답을 입력해주세요.");
        }
        if(reward.getProductId() == missionAnswer){
            //만약 실 유입수가 유입수를 넘어선다면 포인트 지급 막기
            if(reward.getInflowCount() > reward.getActualInflowCount()){
                reward.setRewardStatus(RewardStatus.INACTIVE);
//                new Reward(rewardMissionDTO);
                rewardCommandRepository.save(reward);
                throw new IllegalStateException("끝난 미션입니다.");
            } else{
                PointDetailDTO pointDetailDTO = new PointDetailDTO();
                pointDetailDTO.setUserId(userId);
                pointDetailDTO.setPointAction(PointAction.POINT_DEPOSIT);
                pointDetailDTO.setPointDate(now());
                pointDetailDTO.setPointDelta(reward.getRewardPoint());
                updatePointService.processPointTransaction(pointDetailDTO);
                //실유입수 칼럼에 저장코드
                reward.setInflowCount(reward.getInflowCount() + 1);

                rewardCommandRepository.save(reward);
            }
            return true;
        } else{
            return false;
        }
    }
}
