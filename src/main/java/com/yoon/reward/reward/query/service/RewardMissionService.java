package com.yoon.reward.reward.query.service;

import com.yoon.reward.point.command.application.service.UpdatePointService;
import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import com.yoon.reward.mapper.RewardMapper;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Service
@Transactional
public class RewardMissionService {

    private final RewardMapper rewardMapper;

    @Autowired
    public RewardMissionService(RewardMapper rewardMapper, UserQueryRepository userQueryRepository,
                                UpdatePointService updatePointService) {
        this.rewardMapper = rewardMapper;

    }

    //리워드 하나 조회해서 리워드 미션하기쪽에 데이터 뿌리기
    public RewardMissionDTO rewardMission(Long rewardNo){
        Reward reward = rewardMapper.findRewardByNo(rewardNo);
        if (reward == null) {
            throw new IllegalArgumentException("해당 미션을 찾을 수 없습니다.");
        }
        RewardMissionDTO rewardMissionDTO = new RewardMissionDTO(reward.getRewardPoint(), reward.getKeyword() , reward.getAdvertiserChannel(), reward.getProductName(), reward.getRewardProductPrice());
        return rewardMissionDTO;
    }

    //미션 등록한거 리스트 보기(실유입수가 유입수 넘어간 것들 자동으로 비활성화처리, 화면에서 보이지 않기)-사용자화면
    public List<RewardMissionDTO> rewardMissionList(){
        return rewardMapper.findRewardAll()
                .stream()
                .map(reward -> new RewardMissionDTO((Long)reward.get("reward_no"), (Long)reward.get("reward_point")))  // rewardNo와 rewardPoint 매핑
                .collect(Collectors.toList());
    }


    //등록자 화면에서 본인것만 리워드 보여주기(활성화 이전 날짜 비활성화로 수정가능하게, 종료 이후 날짜 비할성화로 수정못하게)

}
