package com.dondoc.service;

import com.dondoc.dto.ApiResponse;
import com.dondoc.dto.Farms;
import com.dondoc.entity.Farm;
import com.dondoc.entity.FarmMember;
import com.dondoc.exception.ApiException;
import com.dondoc.repository.FarmMemberRepository;
import com.dondoc.repository.FarmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmService {
    private final FarmRepository farmRepository;
    private final FarmMemberRepository farmMemberRepository;

    public FarmService(FarmRepository farmRepository, FarmMemberRepository farmMemberRepository){
        this.farmRepository = farmRepository;
        this.farmMemberRepository = farmMemberRepository;
    }

    public List<Farms.Farm> getFarms(){
        List<Farm> entities = farmRepository.findAll();
        return entities.stream()
                .map(entity -> new Farms.Farm(
                        entity.getId(),
                        entity.getName(),
                        entity.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public List<Farms.Member> getFarmMembers(){
        List<FarmMember> entities = farmMemberRepository.findAll();
        return entities.stream()
                .map(entity -> new Farms.Member(
                        entity.getId(),
                        entity.getUserId(),
                        entity.getFarmId(),
                        entity.getJoinedAt()
                )).collect(Collectors.toList());
    }

    public void createFarm(Farms.Farm dto){
        Farm farm = new Farm(
                null, dto.getName(), dto.getCreatedAt()
        );
        farmRepository.save(farm);
    }

    public void createFarmMember(Farms.Member dto){
        FarmMember farmMember = new FarmMember(
                null, dto.getUserId(), dto.getFarmId(), dto.getJoinedAt()
        );
        farmMemberRepository.save(farmMember);

    }

    //  1. farm_members에서 해당 유저 삭제
    //  2. 남은 멤버 수 확인
    //  3. 0명이면 농장도 삭제
    //  4. 응답 반환 (명세서에 message 없어서 null)
    @Transactional
    public ApiResponse<Farms.LeaveResponse> leaveFarm(Long farmId, Long userId) {
        if (userId == null) {
            throw new ApiException(
                    HttpStatus.UNAUTHORIZED,
                    "인증되지 않은 사용자입니다."
            );
        }

        int deletedCount = farmMemberRepository.deleteByFarmIdAndUserId(farmId, userId);
        if (deletedCount == 0) {
            throw new ApiException(HttpStatus.NOT_FOUND, "농장 멤버를 찾을 수 없습니다.");
        }

        int remainCount = farmMemberRepository.countByFarmId(farmId);
        if (remainCount == 0) {
            farmRepository.deleteById(farmId);
        }

        Farms.LeaveResponse data = new Farms.LeaveResponse(farmId, userId);
        return new ApiResponse<>(true, data, null);
    }

}
