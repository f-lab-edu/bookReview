package com.example.moduleapp.controller;

import com.example.modulecore.dto.ChallengeCompletionRequest;
import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.ChallengeProgressUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/challenges")
@Tag(name = "독서 챌린지 API", description = "사용자의 독서 챌린지를 관리하는 API")
public class ChallengeController {

    private final Map<Long, ChallengeDto> challengeMap = new HashMap<>();
    private final Map<Long, List<ChallengeDto>> userChallenges = new HashMap<>();
    private Long challengeIdCounter = 1L;

    @PostMapping
    @Operation(summary = "독서 챌린지 생성", description = "사용자가 새로운 독서 챌린지를 시작합니다.")
    public ResponseEntity<ChallengeDto> createChallenge(@RequestBody ChallengeDto challengeDto) {
        challengeDto.setId(challengeIdCounter++);
        challengeMap.put(challengeDto.getId(), challengeDto);
        userChallenges.computeIfAbsent(challengeDto.getUserId(), k -> new ArrayList<>()).add(challengeDto);
        return ResponseEntity.ok(challengeDto);
    }

    @PutMapping("/{challengeId}/progress")
    @Operation(summary = "독서 챌린지 진행 상황 업데이트", description = "사용자가 읽은 책 수를 업데이트합니다.")
    public ResponseEntity<String> updateChallengeProgress(@PathVariable Long challengeId, @RequestBody ChallengeProgressUpdateRequest request) {
        ChallengeDto challenge = challengeMap.get(challengeId);
        if (challenge == null) {
            return ResponseEntity.badRequest().body("챌린지를 찾을 수 없습니다.");
        }
        challenge.setBooksRead(request.getBooksRead());
        return ResponseEntity.ok("독서 챌린지 진행 상황이 업데이트되었습니다.");
    }

    @GetMapping("/{challengeId}")
    @Operation(summary = "독서 챌린지 상세 조회", description = "특정 독서 챌린지의 정보를 조회합니다.")
    public ResponseEntity<ChallengeDto> getChallenge(@PathVariable Long challengeId) {
        ChallengeDto challenge = challengeMap.get(challengeId);
        if (challenge == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(challenge);
    }
//public ResponseEntity<List<ChallengeDto>> getUserChallenges(@PathVariable Long userId) {
    @PutMapping("/{challengeId}/complete")
    @Operation(summary = "독서 챌린지 완료", description = "사용자가 챌린지를 완료했음을 표시합니다.")
    public ResponseEntity<String> completeChallenge(@PathVariable Long challengeId, @RequestBody ChallengeCompletionRequest request) {
        ChallengeDto challenge = challengeMap.get(challengeId);
        if (challenge == null) {
            return ResponseEntity.badRequest().body("챌린지를 찾을 수 없습니다.");
        }
        challenge.setCompleted(true);
        return ResponseEntity.ok("독서 챌린지가 완료되었습니다!");
    }
}
