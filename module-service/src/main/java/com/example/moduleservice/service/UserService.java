package com.example.moduleservice.service;

import com.example.modulecore.dto.*;
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
        return userRepository.saveUser(request);
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
