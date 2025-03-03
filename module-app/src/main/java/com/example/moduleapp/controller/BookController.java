package com.example.moduleapp.controller;

import com.example.modulecore.dto.BookDto;
import com.example.modulecore.dto.ReadStatusUpdateRequest;
import com.example.modulecore.dto.WantToReadRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@Tag(name = "도서 API", description = "도서 검색 및 정보 제공 API")
public class BookController {
    private final Map<Long, Map<Long, Boolean>> userReadStatus = new HashMap<>();

    @GetMapping("/search")
    @Operation(summary = "도서 검색", description = "제목, 저자, 장르를 기반으로 도서를 검색합니다.")
    public ResponseEntity<List<BookDto>> searchBooks(
            @RequestParam String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre) {

        // 더미 데이터 반환
        List<BookDto> books = List.of(
                new BookDto(1L, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.5),
                new BookDto(2L, "1984", "George Orwell", "Dystopian", 4.7),
                new BookDto(3L, "To Kill a Mockingbird", "Harper Lee", "Fiction", 4.8)
        );

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}/search")
    @Operation(summary = "도서 정보 조회", description = "책 ID를 이용하여 도서 상세 정보를 조회합니다.")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) {
        BookDto book = new BookDto(bookId, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.5);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{bookId}/searchStoreReviews")
    @Operation(summary = "서점 리뷰 크롤링", description = "서점 리뷰 크롤링을 통해 리뷰 정보를 제공합니다.")
    // 리스트 스트링
    public ResponseEntity<String> getBookReviews(@PathVariable Long bookId) {
        // 추천이 높은 가장 최신 리뷰 5개씩만 가져온다.
        return ResponseEntity.ok("서점 리뷰 내용 (크롤링 데이터 기반)");
    }

    // bookdto 나
    @GetMapping("/popular")
    @Operation(summary = "인기 도서 조회", description = "현재 가장 인기 있는 도서 리스트를 조회합니다.")
    public ResponseEntity<List<BookDto>> getPopularBooks() {
        List<BookDto> popularBooks = List.of(
                new BookDto(1L, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.5),
                new BookDto(2L, "Harry Potter", "J.K. Rowling", "Fantasy", 4.9)
        );
        return ResponseEntity.ok(popularBooks);
    }

    // bookdto나 true/false. request를 리턴하는건 아니고 잘 처리된 dtoㄹ르 반환.
    // string으로 나가면 json 나갈때 파싱될 때 에러날 수 있으니까
    @PostMapping("/{bookId}/addWantToRead")
    @Operation(summary = "읽고 싶은 책 추가", description = "사용자가 읽고 싶은 책을 목록에 추가합니다.")
    public ResponseEntity<String> addWantToRead(@RequestBody WantToReadRequest request) {
        return ResponseEntity.ok("책이 읽고 싶은 목록에 추가되었습니다.");
    }

    // 리턴 안해도 될듯
    // bookdto
    @PutMapping("/{bookId}/markBookAsRead")
    @Operation(summary = "책 읽음 상태 업데이트", description = "사용자가 특정 책을 다 읽었다는 상태를 설정합니다.")
    public ResponseEntity<String> markBookAsRead(@PathVariable Long bookId, @RequestBody ReadStatusUpdateRequest request) {
        return ResponseEntity.ok("책이 '읽음' 상태로 업데이트되었습니다.");
    }
}