package com.yoon.reward.reward.command.application.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import com.yoon.reward.mapper.RewardMapper;
import com.yoon.reward.point.command.application.dto.PointDetailDTO;
import com.yoon.reward.point.command.application.service.UpdatePointService;
import com.yoon.reward.point.command.domain.aggregate.PointAction;
import com.yoon.reward.point.command.domain.repository.PointCommandRepository;
import com.yoon.reward.reward.command.domain.aggregate.Reward;
import com.yoon.reward.reward.command.domain.aggregate.RewardStatus;
import com.yoon.reward.reward.command.domain.repository.RewardCommandRepository;
import com.yoon.reward.reward.query.dto.RewardMissionDTO;
import com.yoon.reward.reward.query.repository.RewardQueryRepository;
import com.yoon.reward.user.command.domain.aggregate.User;
import com.yoon.reward.user.query.repository.UserQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RewardMissionWriteService {
    private final PointCommandRepository pointCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final UpdatePointService updatePointService;
    private final RewardMapper rewardMapper;
    private final RewardCommandRepository rewardCommandRepository;
    private final RewardQueryRepository rewardQueryRepository;

    public RewardMissionWriteService(PointCommandRepository pointCommandRepository, UserQueryRepository userQueryRepository,
                                     UpdatePointService updatePointService, RewardMapper rewardMapper,
                                     RewardCommandRepository rewardCommandRepository, RewardQueryRepository rewardQueryRepository) {
        this.pointCommandRepository = pointCommandRepository;
        this.userQueryRepository = userQueryRepository;
        this.updatePointService = updatePointService;
        this.rewardMapper = rewardMapper;
        this.rewardCommandRepository = rewardCommandRepository;
        this.rewardQueryRepository = rewardQueryRepository;
    }

    //미션 등록
    public void rewardMissionWrite(RewardMissionDTO rewardMissionDTO){
        // null 또는 빈 값 체크
        if (rewardMissionDTO == null) {
            throw new IllegalArgumentException("미션 등록 정보가 입력되지 않습니다.");
        }

        if (rewardMissionDTO.getAdvertiserId() == null || rewardMissionDTO.getAdvertiserId().isEmpty()) {
            throw new IllegalArgumentException("사용자 ID는 필수 항목입니다.");
        }
        //광고주 id가 있는지 확인하는 코드 작성(나중에 필요할때)

        if (rewardMissionDTO.getProductURL() == null || rewardMissionDTO.getProductURL().isEmpty()) {
            throw new IllegalArgumentException("상품URL은 필수 항목입니다.");
        }

        if (rewardMissionDTO.getKeyword() == null || rewardMissionDTO.getKeyword().isEmpty()) {
            throw new IllegalArgumentException("키워드는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getAdvertiserChannel() == null || rewardMissionDTO.getAdvertiserChannel().isEmpty()) {
            throw new IllegalArgumentException("판매 채널은 필수 항목입니다.");
        }

        if (rewardMissionDTO.getRewardProductPrice() <= 0) {
            throw new IllegalArgumentException("리워드 상품 가격은 0보다 커야 합니다.");
        }

        if (rewardMissionDTO.getProductId() == null || rewardMissionDTO.getProductId().isEmpty()) {
            throw new IllegalArgumentException("상품ID는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getProductName() == null || rewardMissionDTO.getProductName().isEmpty()) {
            throw new IllegalArgumentException("상품이름은 필수 항목입니다.");
        }

        if (rewardMissionDTO.getPriceComparison() == null || rewardMissionDTO.getPriceComparison().isEmpty()) {
            throw new IllegalArgumentException("가격비교여부는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getRewardStartDate() == null) {
            throw new IllegalArgumentException("시작 날짜는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getRewardEndDate() == null) {
            throw new IllegalArgumentException("종료 날짜는 필수 항목입니다.");
        }

        // 시작 날짜가 오늘 날짜보다 이전인 경우 예외 처리
        if (rewardMissionDTO.getRewardStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("시작 날짜는 오늘 이후여야 합니다.");
        }

        // 날짜 유효성 검사
        LocalDate startDate = rewardMissionDTO.getRewardStartDate();
        LocalDate endDate = rewardMissionDTO.getRewardEndDate();
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        // 날짜에 따른 추가 포인트 차감 설정
        Long datePointsToDeduct = 0L;
        if (daysBetween == 10) {
            datePointsToDeduct = 10L;
        } else if (daysBetween == 30) {
            datePointsToDeduct = 30L;
        } else {
            throw new IllegalArgumentException("리워드 기간은 시작일과 종료일을 포함하여 10일 또는 30일 중 하나여야 합니다.");
        }

        if (rewardMissionDTO.getInflowCount() <= 0) {
            throw new IllegalArgumentException("유입수는 0보다 커야 합니다.");
        }

        Optional<User> optionalUser = userQueryRepository.findByUserId(rewardMissionDTO.getSalesId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        User user = optionalUser.get();
        // 포인트 차감 계산: 유입수 * 리워드 포인트 * 날짜 (10일이면 10, 30일이면 30)
        //원래 위의 계산 식에서  *100더 하는건데 그거 뺐음, 나중에 영업자에따라 차등 돈 +해서 할거임.
        Long rewardSetPoint = rewardMissionDTO.getRewardPoint();
        Long inflowPointsToDeduct = rewardMissionDTO.getInflowCount() * rewardSetPoint;
        Long currentUserPoints = user.getUserPoint();

        Long totalPointsToDeduct = inflowPointsToDeduct * datePointsToDeduct;


        // 차감될 포인트가 사용자 포인트보다 클 경우 예외 처리
        if (currentUserPoints < totalPointsToDeduct) {
            throw new IllegalArgumentException(
                    "사용자의 포인트가 부족합니다. 필요한 포인트: " + totalPointsToDeduct + ", 현재 포인트: " + currentUserPoints
            );
        }
        PointDetailDTO pointDetailDTO = new PointDetailDTO();
        pointDetailDTO.setUserId(rewardMissionDTO.getSalesId()); // 차감 대상 사용자 ID 설정
        pointDetailDTO.setPointAction(PointAction.POINT_WITHDRAW); // 포인트 차감 동작 설정
        pointDetailDTO.setPointDate(LocalDateTime.now()); // 현재 시간 설정
        pointDetailDTO.setPointDelta(totalPointsToDeduct); // 총 차감 포인트 설정
        updatePointService.processPointTransaction(pointDetailDTO);

        //아래 코드 안됨 추후 수정
        Long maxCode = rewardQueryRepository.findMaxRewardCode();
        if (maxCode == null || maxCode == 0L) {
            maxCode = 9999L;
        }
        rewardMissionDTO.setRewardId(maxCode + 1);
//        rewardMissionDTO.setRewardId(maxCode != null ? maxCode + 1 : 10000); // 여기서 코드 설정
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
            throw new IllegalArgumentException("영업자 ID는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getProductURL() == null || rewardMissionDTO.getProductURL().isEmpty()) {
            throw new IllegalArgumentException("상품URL은 필수 항목입니다.");
        }

        if (rewardMissionDTO.getKeyword() == null || rewardMissionDTO.getKeyword().isEmpty()) {
            throw new IllegalArgumentException("키워드는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getAdvertiserChannel() == null || rewardMissionDTO.getAdvertiserChannel().isEmpty()) {
            throw new IllegalArgumentException("판매 채널은 필수 항목입니다.");
        }

        if (rewardMissionDTO.getRewardProductPrice() <= 0) {
            throw new IllegalArgumentException("리워드 상품 가격은 0보다 커야 합니다.");
        }

        if (rewardMissionDTO.getProductId() == null || rewardMissionDTO.getProductId().isEmpty()) {
            throw new IllegalArgumentException("상품ID는 필수 항목입니다.");
        }

        if (rewardMissionDTO.getProductName() == null || rewardMissionDTO.getProductName().isEmpty()) {
            throw new IllegalArgumentException("상품이름은 필수 항목입니다.");
        }

        if (rewardMissionDTO.getPriceComparison() == null || rewardMissionDTO.getPriceComparison().isEmpty()) {
            throw new IllegalArgumentException("가격비교여부는 필수 항목입니다.");
        }



        // 유입수 수정 못함, 활성화 수정 못함
        existingReward.setSalesId(rewardMissionDTO.getSalesId());
        existingReward.setKeyword(rewardMissionDTO.getKeyword());
        existingReward.setAdvertiserChannel(rewardMissionDTO.getAdvertiserChannel());
        existingReward.setRewardProductPrice(rewardMissionDTO.getRewardProductPrice());
        existingReward.setProductId(rewardMissionDTO.getProductId());
        existingReward.setOptionId(rewardMissionDTO.getOptionId());
        existingReward.setPriceComparison(rewardMissionDTO.getPriceComparison());
        existingReward.setRewardMemo(rewardMissionDTO.getRewardMemo());
        existingReward.setProductURL(rewardMissionDTO.getProductURL());
        existingReward.setProductName(rewardMissionDTO.getProductName());

        Reward reward = new Reward(existingReward);
        rewardCommandRepository.save(reward);
    }
}
