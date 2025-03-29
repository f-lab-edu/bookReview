package com.example.moduleapp.controller;

import com.example.modulecore.domain.User;
import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.LoginRequest;
import com.example.modulecore.dto.SignupRequest;
import com.example.modulecore.dto.UserPreferencesDto;
import com.example.moduleservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "JWT")
@Tag(name = "유저 인증 API", description = "회원가입 및 로그인 API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입을 수행하고 JWT 토큰을 반환합니다.")
    public ResponseEntity<Map<String, String>> register(@RequestBody SignupRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userService.registerUser(user);

        // 로그인 시 평문 비밀번호로 다시 요청 생성
        User loginUser = User.builder()
                .email(request.getEmail())
                .password(request.getPassword()) // 평문
                .build();

        String token = userService.login(loginUser);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 로그인을 수행하고 JWT 토큰을 반환합니다.")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        String token = userService.login(user);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
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