package com.example.moduleservice.repository;


import com.example.modulecore.dto.ChallengeDto;
import com.example.modulecore.dto.SignupRequest;
import com.example.modulecore.dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<Long, UserDto> users = new HashMap<>();
    private final Map<Long, List<ChallengeDto>> userChallenges = new HashMap<>();
    private long userIdSequence = 1L;

    public UserDto saveUser(SignupRequest request) {
        UserDto newUser = new UserDto(userIdSequence, request.getName(), request.getEmail(), request.getPassword());
        users.put(userIdSequence, newUser);
        userChallenges.put(userIdSequence, new ArrayList<>());
        userIdSequence++;
        return newUser;
    }

    public List<ChallengeDto> getUserChallenges(Long userId) {
        return userChallenges.getOrDefault(userId, new ArrayList<>());
    }

}
