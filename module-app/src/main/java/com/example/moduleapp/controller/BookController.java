package com.example.moduleapp.controller;

import com.example.modulecore.dto.BookDto;
import com.example.modulecore.dto.ReadStatusUpdateRequest;
import com.example.modulecore.dto.ReviewDto;
import com.example.modulecore.dto.WantToReadRequest;
import com.example.moduleservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@Tag(name = "도서 API", description = "도서 검색 및 정보 제공 API")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    @Operation(summary = "도서 검색", description = "제목, 저자, 장르를 기반으로 도서를 검색합니다.")
    public ResponseEntity<List<BookDto>> searchBooks(
            @RequestParam String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre) {
        return ResponseEntity.ok(bookService.searchBooks(title, author, genre));
    }

    @GetMapping("/{bookId}/search")
    @Operation(summary = "도서 정보 조회", description = "책 ID를 이용하여 도서 상세 정보를 조회합니다.")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{bookId}/searchStoreReviews")
    @Operation(summary = "서점 리뷰 크롤링", description = "서점 리뷰 크롤링을 통해 리뷰 정보를 제공합니다.")
    // 리스트 스트링
    public ResponseEntity<List<ReviewDto>> getBookReviews(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookReviews(bookId));
    }

    @GetMapping("/popular")
    @Operation(summary = "인기 도서 조회", description = "현재 가장 인기 있는 도서 리스트를 조회합니다.")
    public ResponseEntity<List<BookDto>> getPopularBooks() {
        return ResponseEntity.ok(bookService.getPopularBooks());
    }

    @PostMapping("/{bookId}/addWantToRead")
    @Operation(summary = "읽고 싶은 책 추가", description = "사용자가 읽고 싶은 책을 목록에 추가합니다.")
    public ResponseEntity<BookDto> addWantToRead(@RequestBody WantToReadRequest request) {
        BookDto book = new BookDto(1L, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.5);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{bookId}/markBookAsRead")
    @Operation(summary = "책 읽음 상태 업데이트", description = "사용자가 특정 책을 다 읽었다는 상태를 설정합니다.")
    public ResponseEntity<BookDto> markBookAsRead(@PathVariable Long bookId, @RequestBody ReadStatusUpdateRequest request) {
        BookDto book = new BookDto(1L, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.5);
        return ResponseEntity.ok(book);
    }
}