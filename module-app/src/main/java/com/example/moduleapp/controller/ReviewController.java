package com.example.moduleapp.controller;

import com.example.modulecore.dto.ReviewDto;
import com.example.modulecore.dto.ReviewRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "리뷰 API", description = "도서 리뷰 관련 API")
public class ReviewController {

    private final List<ReviewDto> reviews = new ArrayList<>();

    @GetMapping("/{bookId}")
    @Operation(summary = "도서 리뷰 조회", description = "특정 도서의 리뷰 목록을 조회합니다.")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long bookId) {
        return ResponseEntity.ok(new ReviewDto(1L, bookId, 1L, "book content", "book summary", 4.5));
    }

    @GetMapping("/{bookId}/aiSummary")
    @Operation(summary = "AI 요약 리뷰", description = "책에 대한 AI 기반 요약 리뷰를 제공합니다.")
    public ResponseEntity<String> getReviewSummary(@PathVariable Long bookId) {
        return ResponseEntity.ok("이 책은 이러이러해서 감동적이라고 3줄 요약된다.");
    }

    // 리뷰가 뭐였는지 보여주면좋을거같음.
    @PostMapping
    @Operation(summary = "리뷰 작성", description = "사용자가 책을 읽고 리뷰와 평점을 남깁니다.")
    public ResponseEntity<String> addReview(@RequestBody ReviewRequest reviewRequest) {
        ReviewDto review = new ReviewDto(
                (long) (reviews.size() + 1),
                reviewRequest.getBookId(),
                reviewRequest.getUserId(),
                reviewRequest.getContent(),
                reviewRequest.getSummary(),
                reviewRequest.getRating()
        );
        reviews.add(review);
        return ResponseEntity.ok("리뷰가 성공적으로 추가되었습니다.");
    }
}