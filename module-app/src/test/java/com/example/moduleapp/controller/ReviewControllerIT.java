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
class ReviewControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetReview() throws Exception {
        mockMvc.perform(get("/api/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1));
    }

    @Test
    void testGetReviewSummary() throws Exception {
        mockMvc.perform(get("/api/reviews/aiSummary/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("이 책은 이러이러해서 감동적이라고 3줄 요약된다."));
    }

    @Test
    void testAddReview() throws Exception {
        String reviewJson = """
                {
                    "bookId": 1,
                    "userId": 1,
                    "content": "정말 감동적인 책입니다.",
                    "summary": "한 문장으로 요약 가능",
                    "rating": 4.8
                }
                """;

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewJson))
                .andExpect(status().isOk())
                .andExpect(content().string("리뷰가 성공적으로 추가되었습니다."));
    }
}
