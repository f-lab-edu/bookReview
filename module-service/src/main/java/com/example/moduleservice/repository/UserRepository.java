package com.example.moduleservice.repository;


import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.SignupRequest;
import com.example.modulecore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User saveUser(SignupRequest request) {
        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        return save(newUser);
    }

    default Optional<User> getUserById(Long userId) {
        return findById(userId);
    }

    default List<ChallengeDto> getUserChallenges(Long userId) {
        // userId 별로 DB에서 챌린지 목록을 가져옴
        return List.of(); // 챌린지 리스트를 반환해야 함
    }
}
