package com.example.moduleapp.controller;

import com.example.modulecore.dto.ReviewDto;
import com.example.modulecore.dto.ReviewRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReviewControllerTest {

    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        reviewController = new ReviewController();
    }

    @Test
    void testGetReview() {
        ResponseEntity<ReviewDto> response = reviewController.getReview(1L);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getBookId()).isEqualTo(1L);
    }

    @Test
    void testGetReviewSummary() {
        ResponseEntity<String> response = reviewController.getReviewSummary(1L);
        assertThat(response.getBody()).isEqualTo("이 책은 이러이러해서 감동적이라고 3줄 요약된다.");
    }

    @Test
    void testAddReview() {
        ReviewRequest request = new ReviewRequest(1L, 1L, "정말 감동적인 책입니다!", "한 문장으로 요약 가능", 4.8);
        ResponseEntity<String> response = reviewController.addReview(request);
        assertThat(response.getBody()).isEqualTo("리뷰가 성공적으로 추가되었습니다.");
    }
}
