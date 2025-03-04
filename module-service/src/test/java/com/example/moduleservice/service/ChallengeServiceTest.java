package com.example.moduleservice.service;

import com.example.modulecore.dto.ChallengeCompletionRequest;
import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.ChallengeProgressUpdateRequest;
import com.example.moduleservice.repository.ChallengeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Mockito 사용을 위한 설정
public class ChallengeServiceTest {

    @Mock
    private ChallengeRepository challengeRepository;

    @InjectMocks
    private ChallengeService challengeService;

    private ChallengeDto testChallenge;

    @BeforeEach
    void setUp() {
        testChallenge = new ChallengeDto(1L, 1001L, "읽기 챌린지", 5, 2, false);
    }

    @Test
    void createChallenge_ShouldReturnChallenge() {
        when(challengeRepository.save(any(ChallengeDto.class))).thenReturn(testChallenge);

        ChallengeDto result = challengeService.createChallenge(testChallenge);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("읽기 챌린지", result.getChallengeName());
    }

    @Test
    void getChallengeById_ShouldReturnChallenge_WhenExists() {
        when(challengeRepository.findById(1L)).thenReturn(Optional.of(testChallenge));

        Optional<ChallengeDto> result = challengeService.getChallengeById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void getChallengeById_ShouldReturnEmpty_WhenNotExists() {
        when(challengeRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<ChallengeDto> result = challengeService.getChallengeById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void updateChallengeProgress_ShouldUpdateBooksRead() {
        ChallengeProgressUpdateRequest request = new ChallengeProgressUpdateRequest(3);
        when(challengeRepository.findById(1L)).thenReturn(Optional.of(testChallenge));

        boolean updated = challengeService.updateChallengeProgress(1L, request);

        assertTrue(updated);
        assertEquals(3, testChallenge.getBooksRead());
        verify(challengeRepository, times(1)).update(testChallenge);
    }

    @Test
    void completeChallenge_ShouldMarkAsCompleted() {
        ChallengeCompletionRequest request = new ChallengeCompletionRequest();
        when(challengeRepository.findById(1L)).thenReturn(Optional.of(testChallenge));

        boolean completed = challengeService.completeChallenge(1L, request);

        assertTrue(completed);
        assertTrue(testChallenge.isCompleted());
        verify(challengeRepository, times(1)).update(testChallenge);
    }
}
