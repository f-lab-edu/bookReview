package com.example.moduleservice.repository;

import com.example.modulecore.dto.ChallengeDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChallengeRepository {
    private final Map<Long, ChallengeDto> challengeMap = new HashMap<>();
    private final Map<Long, List<ChallengeDto>> userChallenges = new HashMap<>();
    private Long challengeIdCounter = 1L;

    public ChallengeDto save(ChallengeDto challengeDto) {
        challengeDto.setId(challengeIdCounter++);
        challengeMap.put(challengeDto.getId(), challengeDto);
        userChallenges.computeIfAbsent(challengeDto.getUserId(), k -> new ArrayList<>()).add(challengeDto);
        return challengeDto;
    }

    public Optional<ChallengeDto> findById(Long challengeId) {
        return Optional.ofNullable(challengeMap.get(challengeId));
    }

    public void update(ChallengeDto challengeDto) {
        challengeMap.put(challengeDto.getId(), challengeDto);
    }
}
