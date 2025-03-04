package com.example.moduleapp.controller;

import com.example.moduleservice.service.UserService;
import com.example.modulecore.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "유저 인증 API", description = "회원가입 및 로그인 API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public ResponseEntity<UserDto> register(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 로그인을 수행합니다.")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/preferences")
    @Operation(summary = "관심 장르 설정", description = "회원이 관심 있는 장르를 설정합니다.")
    public ResponseEntity<String> setUserPreferences(@RequestBody UserPreferencesDto request) {
        return ResponseEntity.ok(userService.setUserPreferences(request));
    }

    @GetMapping("/{userId}/challenges")
    @Operation(summary = "사용자의 독서 챌린지 목록 조회", description = "사용자가 참여한 모든 챌린지를 조회합니다.")
    public ResponseEntity<List<ChallengeDto>> getUserChallenges(@PathVariable Long userId) {
         return ResponseEntity.ok(userService.getUserChallenges(userId));
    }
}