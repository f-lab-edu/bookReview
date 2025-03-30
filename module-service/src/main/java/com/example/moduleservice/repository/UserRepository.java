package com.example.moduleservice.repository;

import com.example.modulecore.domain.User;
import com.example.modulecore.dto.ChallengeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User saveUser(User newUser) {
        return save(newUser);
    }

    default Optional<User> getUserById(Long userId) {
        return findById(userId);
    }

    Optional<User> findByEmail(String email);

    default List<ChallengeDto> getUserChallenges(Long userId) {
        // userId 별로 DB에서 챌린지 목록을 가져옴
        return List.of(); // 챌린지 리스트를 반환해야 함
    }
}
