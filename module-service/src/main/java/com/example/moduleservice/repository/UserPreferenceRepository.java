package com.example.moduleservice.repository;

import com.example.modulecore.domain.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    void deleteAllByUserId(Long userId);
}
