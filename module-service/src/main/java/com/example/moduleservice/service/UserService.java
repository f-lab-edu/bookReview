package com.example.moduleservice.service;

import com.example.modulecore.domain.User;
import com.example.modulecore.domain.UserPreference;
import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.UserPreferencesDto;
import com.example.moduleservice.config.JwtTokenProvider;
import com.example.moduleservice.repository.UserPreferenceRepository;
import com.example.moduleservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserPreferenceRepository preferenceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveUser(user);
        return "회원가입이 완료되었습니다.";
    }

    public String login(User loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(user.getId());
    }

    public String setUserPreferences(UserPreferencesDto preference) {
        User user = userRepository.findById(preference.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        preferenceRepository.deleteAllByUserId(user.getId());

        List<UserPreference> preferences = preference.getGenres().stream()
                .map(genre -> UserPreference.builder()
                        .user(user)
                        .genre(genre)
                        .build())
                .collect(Collectors.toList());

        preferenceRepository.saveAll(preferences);
        return "관심 장르 설정이 완료되었습니다.";
    }

    public List<ChallengeDto> getUserChallenges(Long userId) {
        return userRepository.getUserChallenges(userId);
    }
}
