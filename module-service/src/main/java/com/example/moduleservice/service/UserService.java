package com.example.moduleservice.service;

import com.example.modulecore.dto.*;
import com.example.modulecore.domain.User;
import com.example.moduleservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto registerUser(SignupRequest request) {
        User savedUser = userRepository.saveUser(request);
        return UserDto.fromEntity(savedUser);
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return UserDto.fromEntity(user);
    }

    public String login(LoginRequest request) {
        return "Bearer dummy-jwt-token";
    }

    public String setUserPreferences(UserPreferencesDto preferences) {
        return "관심 장르 설정이 완료되었습니다.";
    }

    public List<ChallengeDto> getUserChallenges(Long userId) {
        return userRepository.getUserChallenges(userId);
    }
}
