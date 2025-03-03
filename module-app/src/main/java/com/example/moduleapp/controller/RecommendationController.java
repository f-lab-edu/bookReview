package com.example.moduleapp.controller;

import com.example.modulecore.dto.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "추천 API", description = "사용자의 관심 장르 기반 추천 도서 제공")
public class RecommendationController {

    @GetMapping("/{userId}")
    @Operation(summary = "맞춤형 추천 도서 조회", description = "사용자의 관심 장르 및 읽은 책 데이터를 기반으로 추천 도서를 제공합니다.")
    public ResponseEntity<List<BookDto>> getRecommendations(@PathVariable Long userId) {
        List<BookDto> recommendations = List.of(
                new BookDto(4L, "Dune", "Frank Herbert", "Science Fiction", 4.6),
                new BookDto(5L, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 4.8)
        );
        return ResponseEntity.ok(recommendations);
    }
}