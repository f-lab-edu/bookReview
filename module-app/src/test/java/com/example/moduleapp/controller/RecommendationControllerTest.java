package com.example.moduleapp.controller;

import com.example.modulecore.dto.BookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecommendationControllerTest {

    private RecommendationController recommendationController;

    @BeforeEach
    void setUp() {
        recommendationController = new RecommendationController();
    }

    @Test
    void testGetRecommendations() {
        ResponseEntity<List<BookDto>> response = recommendationController.getRecommendations(1L);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().size()).isEqualTo(2);
        assertThat(response.getBody().get(0).getTitle()).isEqualTo("Dune");
    }
}
