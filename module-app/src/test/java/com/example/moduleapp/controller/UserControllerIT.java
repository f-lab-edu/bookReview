package com.example.moduleapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterUser() throws Exception {
        String userJson = """
                {
                    "name": "Sumin",
                    "email": "sumin@test.com",
                    "password": "password123"
                }
                """;

        mockMvc.perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sumin"));
    }

    @Test
    void testLoginUser() throws Exception {
        String loginJson = """
                {
                    "email": "sumin@test.com",
                    "password": "password123"
                }
                """;

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Bearer dummy-jwt-token"));
    }

    @Test
    void testSetUserPreferences() throws Exception {
        String preferencesJson = """
                {
                    "userId": 1,
                    "preferredGenres": ["Fantasy", "Romance"]
                }
                """;

        mockMvc.perform(post("/api/users/preferences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(preferencesJson))
                .andExpect(status().isOk())
                .andExpect(content().string("관심 장르 설정이 완료되었습니다."));
    }
}
