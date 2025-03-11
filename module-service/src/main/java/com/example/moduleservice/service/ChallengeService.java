package com.example.moduleservice.service;

import com.example.modulecore.dto.ChallengeCompletionRequest;
import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.ChallengeProgressUpdateRequest;
import com.example.moduleservice.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public ChallengeDto createChallenge(ChallengeDto challengeDto) {
        return challengeRepository.save(challengeDto);
    }

    public Optional<ChallengeDto> getChallengeById(Long challengeId) {
        return challengeRepository.findById(challengeId);
    }

    public boolean updateChallengeProgress(Long challengeId, ChallengeProgressUpdateRequest request) {
        Optional<ChallengeDto> challengeOpt = challengeRepository.findById(challengeId);
        if (challengeOpt.isEmpty()) {
            return false;
        }
        ChallengeDto challenge = challengeOpt.get();
        challenge.setBooksRead(request.getBooksRead());
        challengeRepository.update(challenge);
        return true;
    }

    public boolean completeChallenge(Long challengeId, ChallengeCompletionRequest request) {
        Optional<ChallengeDto> challengeOpt = challengeRepository.findById(challengeId);
        if (challengeOpt.isEmpty()) {
            return false;
        }
        ChallengeDto challenge = challengeOpt.get();
        challenge.setCompleted(true);
        challengeRepository.update(challenge);
        return true;
    }
}
