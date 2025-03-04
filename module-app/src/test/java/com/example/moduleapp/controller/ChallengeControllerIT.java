package com.example.moduleapp.controller;

import com.example.modulecore.dto.ChallengeCompletionRequest;
import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.ChallengeProgressUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChallengeControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/challenges";
    }

    @Test
    void createChallenge_ShouldReturnChallenge() {
        // Given
        ChallengeDto challengeDto = new ChallengeDto(null, 1001L, "책 읽기 챌린지", 5, 0, false);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ChallengeDto> request = new HttpEntity<>(challengeDto, headers);

        // When
        ResponseEntity<ChallengeDto> response = restTemplate.postForEntity(getBaseUrl(), request, ChallengeDto.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("책 읽기 챌린지", response.getBody().getChallengeName());
    }

    @Test
    void getChallenge_ShouldReturnChallenge_WhenExists() {
        // Given (먼저 챌린지를 생성)
        ChallengeDto challengeDto = new ChallengeDto(null, 1001L, "테스트 챌린지", 5, 0, false);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ChallengeDto> request = new HttpEntity<>(challengeDto, headers);
        ResponseEntity<ChallengeDto> createdChallengeResponse = restTemplate.postForEntity(getBaseUrl(), request, ChallengeDto.class);
        Long challengeId = createdChallengeResponse.getBody().getId();

        // When
        ResponseEntity<ChallengeDto> response = restTemplate.getForEntity(getBaseUrl() + "/" + challengeId, ChallengeDto.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("테스트 챌린지", response.getBody().getChallengeName());
    }
    @Test
    void getChallenge_ShouldReturnNotFound_WhenNotExists() {
        ResponseEntity<ChallengeDto> response = restTemplate.getForEntity(getBaseUrl() + "/9999", ChallengeDto.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateChallengeProgress_ShouldUpdateBooksRead() {
        // Given (챌린지 생성)
        ChallengeDto challengeDto = new ChallengeDto(null, 1001L, "진행률 업데이트 챌린지", 5, 0, false);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ChallengeDto> request = new HttpEntity<>(challengeDto, headers);
        ResponseEntity<ChallengeDto> createdChallengeResponse = restTemplate.postForEntity(getBaseUrl(), request, ChallengeDto.class);
        Long challengeId = createdChallengeResponse.getBody().getId();

        // When (진행률 업데이트)
        ChallengeProgressUpdateRequest updateRequest = new ChallengeProgressUpdateRequest(3);
        HttpEntity<ChallengeProgressUpdateRequest> updateRequestEntity = new HttpEntity<>(updateRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(getBaseUrl() + "/" + challengeId + "/progress", HttpMethod.PUT, updateRequestEntity, String.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void completeChallenge_ShouldMarkAsCompleted() {
        // Given (챌린지 생성)
        ChallengeDto challengeDto = new ChallengeDto(null, 1001L, "완료 챌린지", 5, 0, false);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ChallengeDto> request = new HttpEntity<>(challengeDto, headers);
        ResponseEntity<ChallengeDto> createdChallengeResponse = restTemplate.postForEntity(getBaseUrl(), request, ChallengeDto.class);
        Long challengeId = createdChallengeResponse.getBody().getId();

        // When (챌린지 완료)
        ChallengeCompletionRequest completeRequest = new ChallengeCompletionRequest();
        HttpEntity<ChallengeCompletionRequest> completeRequestEntity = new HttpEntity<>(completeRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(getBaseUrl() + "/" + challengeId + "/complete", HttpMethod.PUT, completeRequestEntity, String.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
