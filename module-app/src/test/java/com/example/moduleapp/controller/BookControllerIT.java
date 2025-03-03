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
class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSearchBooks() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("title", "The Great Gatsby"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/api/books/search/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetBookReviews() throws Exception {
        mockMvc.perform(get("/api/books/searchStoreReviews/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("서점 리뷰 내용 (크롤링 데이터 기반)"));
    }

    @Test
    void testGetPopularBooks() throws Exception {
        mockMvc.perform(get("/api/books/popular"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testAddWantToRead() throws Exception {
        String requestJson = """
                {
                    "userId": 1,
                    "bookId": 1
                }
                """;

        mockMvc.perform(post("/api/books/addWantToRead/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("책이 읽고 싶은 목록에 추가되었습니다."));
    }

    @Test
    void testMarkBookAsRead() throws Exception {
        String requestJson = """
                {
                    "userId": 1
                }
                """;

        mockMvc.perform(put("/api/books/markBookAsRead/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("책이 '읽음' 상태로 업데이트되었습니다."));
    }
}
