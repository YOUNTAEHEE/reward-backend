package com.yoon.reward.reward.command.application.service;
import java.time.LocalDate;
import com.yoon.reward.mapper.RewardMapper;
import com.yoon.reward.point.command.application.service.UpdatePointService;
import com.yoon.reward.point.command.domain.repository.PointCommandRepository;
import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import com.yoon.reward.reward.command.domain.repository.RewardCommandRepository;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import org.springframework.stereotype.Service;

@Service
public class RewardMissionWriteService {
    private final PointCommandRepository pointCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final UpdatePointService updatePointService;
    private final RewardMapper rewardMapper;
    private final RewardCommandRepository rewardCommandRepository;

    public RewardMissionWriteService(PointCommandRepository pointCommandRepository,
                                     UserQueryRepository userQueryRepository, UpdatePointService updatePointService,
                                     RewardMapper rewardMapper, RewardCommandRepository rewardCommandRepository) {
        this.pointCommandRepository = pointCommandRepository;
        this.userQueryRepository = userQueryRepository;
        this.updatePointService = updatePointService;
        this.rewardMapper = rewardMapper;
        this.rewardCommandRepository = rewardCommandRepository;
    }

    //미션 등록
    public void rewardMissionWrite(RewardMissionDTO rewardMissionDTO){
        // null 또는 빈 값 체크
        if (rewardMissionDTO == null) {
            throw new IllegalArgumentException("미션 등록 정보가 입력되지 않습니다.");
        }

        if (rewardMissionDTO.getAdvertiserId() == null || rewardMissionDTO.getAdvertiserId().isEmpty()) {
            throw new IllegalArgumentException("광고주 ID는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getSalesId() == null || rewardMissionDTO.getSalesId().isEmpty()) {
            throw new IllegalArgumentException("영업 ID는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getKeyword() == null || rewardMissionDTO.getKeyword().isEmpty()) {
            throw new IllegalArgumentException("키워드는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getSalesChannel() == null || rewardMissionDTO.getSalesChannel().isEmpty()) {
            throw new IllegalArgumentException("판매 채널은 필수 항목입니다.");
        }

        if (rewardMissionDTO.getRewardProductPrice() <= 0) {
            throw new IllegalArgumentException("리워드 상품 가격은 0보다 커야 합니다.");
        }

        if (rewardMissionDTO.getRewardPoint() <= 0) {
            throw new IllegalArgumentException("리워드 포인트는 0보다 커야 합니다.");
        }

        if (rewardMissionDTO.getProductCode() == null || rewardMissionDTO.getProductCode().isEmpty()) {
            throw new IllegalArgumentException("상품 코드는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getRewardStartDate() == null) {
            throw new IllegalArgumentException("시작 날짜는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getRewardEndDate() == null) {
            throw new IllegalArgumentException("종료 날짜는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getInflowCount() <= 0) {
            throw new IllegalArgumentException("유입수는 0보다 커야 합니다.");
        }

        Reward reward = new Reward(rewardMissionDTO);
        rewardCommandRepository.save(reward);
    }

    //미션수정
    public void rewardMissionModify(RewardMissionDTO rewardMissionDTO){
        if (rewardMissionDTO == null) {
            throw new IllegalArgumentException("미션 수정 정보가 입력되지 않습니다.");
        }

        RewardMissionDTO existingReward = rewardMapper.getRewardMissionById(rewardMissionDTO.getRewardNo());

        LocalDate today = LocalDate.now();

        //활성화 된 미션 수정 못함
        if(rewardMissionDTO.getRewardStatus() == RewardStatus.ACTIVE){
            throw new IllegalStateException("활성화된 미션은 수정할 수 없습니다.");
        }

        // 종료 날짜가 이미 지난 경우 수정 불가
        if (existingReward.getRewardEndDate().isBefore(today)) {
            existingReward.setRewardStatus(RewardStatus.INACTIVE);
            throw new IllegalStateException("미션 종료 날짜가 지났으므로 수정할 수 없습니다.");
        }

        // 시작 날짜가 오늘보다 이전이면 수정 가능
        if (existingReward.getRewardStartDate().isBefore(today)) {
            existingReward.setRewardStatus(RewardStatus.INACTIVE);
            throw new IllegalStateException("미션 시작 전에만 수정이 가능합니다.");
        }

        // 각 필드가 null이면 예외 발생
        if (rewardMissionDTO.getSalesId() == null || rewardMissionDTO.getSalesId().isEmpty()) {
            throw new IllegalArgumentException("판매자 ID는 필수 항목입니다.");
        }
        if (rewardMissionDTO.getKeyword() == null || rewardMissionDTO.getKeyword().isEmpty()) {
            throw new IllegalArgumentException("키워드는 필수 항목입니다.");
        }
        if (rewardMissionDTO.getSalesChannel() == null || rewardMissionDTO.getSalesChannel().isEmpty()) {
            throw new IllegalArgumentException("판매 채널은 필수 항목입니다.");
        }
        if (rewardMissionDTO.getRewardProductPrice() == null || rewardMissionDTO.getRewardProductPrice() == 0) {
            throw new IllegalArgumentException("리워드 제품 가격은 필수 항목입니다.");
        }
        if (rewardMissionDTO.getProductCode() == null || rewardMissionDTO.getProductCode().isEmpty()) {
            throw new IllegalArgumentException("제품 코드는 필수 항목입니다.");
        }
        if (rewardMissionDTO.getRewardStartDate() == null) {
            throw new IllegalArgumentException("리워드 시작 날짜는 필수 항목입니다.");
        }
        if (rewardMissionDTO.getRewardEndDate() == null) {
            throw new IllegalArgumentException("리워드 종료 날짜는 필수 항목입니다.");
        }

        // 유입수 수정 못함, 활성화 수정 못함
        existingReward.setSalesId(rewardMissionDTO.getSalesId());
        existingReward.setKeyword(rewardMissionDTO.getKeyword());
        existingReward.setSalesChannel(rewardMissionDTO.getSalesChannel());
        existingReward.setRewardProductPrice(rewardMissionDTO.getRewardProductPrice());
        existingReward.setProductCode(rewardMissionDTO.getProductCode());
        existingReward.setRewardStartDate(rewardMissionDTO.getRewardStartDate());
        existingReward.setRewardEndDate(rewardMissionDTO.getRewardEndDate());
        existingReward.setRewardMemo(rewardMissionDTO.getRewardMemo());
        existingReward.setRewardMemo(rewardMissionDTO.getProductURL());
        existingReward.setRewardMemo(rewardMissionDTO.getProductName());

        Reward reward = new Reward(existingReward);
        rewardCommandRepository.save(reward);
    }
}
