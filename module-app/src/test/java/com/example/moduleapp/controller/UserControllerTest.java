package com.example.moduleapp.controller;

import com.example.modulecore.dto.SignupRequest;
import com.example.modulecore.dto.LoginRequest;
import com.example.modulecore.dto.UserDto;
import com.example.modulecore.dto.UserPreferencesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest {

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController();
    }

    @Test
    void testRegisterUser() {
        SignupRequest request = new SignupRequest();
        request.setName("Sumin");
        request.setEmail("sumin@test.com");
        request.setPassword("password123");

        ResponseEntity<UserDto> response = userController.register(request);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Sumin");
    }

    @Test
    void testLoginUser() {
        LoginRequest request = new LoginRequest();
        request.setEmail("sumin@test.com");
        request.setPassword("password123");

        ResponseEntity<String> response = userController.login(request);
        assertThat(response.getBody()).isEqualTo("Bearer dummy-jwt-token");
    }

    @Test
    void testSetUserPreferences() {
        UserPreferencesDto request = new UserPreferencesDto();
        request.setUserId(1L);
        request.setPreferredGenres(java.util.List.of("Fantasy", "Romance"));

        ResponseEntity<String> response = userController.setUserPreferences(request);

        assertThat(response.getBody()).isEqualTo("관심 장르 설정이 완료되었습니다.");
    }
}
