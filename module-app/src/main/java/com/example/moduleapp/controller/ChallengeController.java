package com.example.moduleapp.controller;

import com.example.modulecore.dto.ChallengeCompletionRequest;
import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.ChallengeProgressUpdateRequest;
import com.example.moduleservice.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/challenges")
@Validated
@Tag(name = "독서 챌린지 API", description = "사용자의 독서 챌린지를 관리하는 API")
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping
    @Operation(summary = "독서 챌린지 생성", description = "사용자가 새로운 독서 챌린지를 시작합니다.")
    public ResponseEntity<ChallengeDto> createChallenge(@Valid @RequestBody ChallengeDto challengeDto) {
        return ResponseEntity.ok(challengeService.createChallenge(challengeDto));
    }

    @PutMapping("/{challengeId}/progress")
    @Operation(summary = "독서 챌린지 진행 상황 업데이트", description = "사용자가 읽은 책 수를 업데이트합니다.")
    public ResponseEntity<String> updateChallengeProgress(@PathVariable Long challengeId, @RequestBody ChallengeProgressUpdateRequest request) {
        boolean isUpdated = challengeService.updateChallengeProgress(challengeId, request);
        if (!isUpdated) {
            return ResponseEntity.badRequest().body("챌린지를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok("독서 챌린지 진행 상황이 업데이트되었습니다.");
    }

    @GetMapping("/{challengeId}")
    @Operation(summary = "독서 챌린지 상세 조회", description = "특정 독서 챌린지의 정보를 조회합니다.")
    public ResponseEntity<ChallengeDto> getChallenge(@PathVariable Long challengeId) {
        Optional<ChallengeDto> challenge = challengeService.getChallengeById(challengeId);
        return challenge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{challengeId}/complete")
    @Operation(summary = "독서 챌린지 완료", description = "사용자가 챌린지를 완료했음을 표시합니다.")
    public ResponseEntity<String> completeChallenge(@PathVariable Long challengeId, @RequestBody ChallengeCompletionRequest request) {
        boolean isCompleted = challengeService.completeChallenge(challengeId, request);
        if (!isCompleted) {
            return ResponseEntity.badRequest().body("챌린지를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok("독서 챌린지가 완료되었습니다!");
    }
}
