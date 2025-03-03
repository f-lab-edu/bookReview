package com.example.moduleapp.controller;

import com.example.modulecore.dto.BookDto;
import com.example.modulecore.dto.ReadStatusUpdateRequest;
import com.example.modulecore.dto.WantToReadRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookControllerTest {

    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController();
    }

    @Test
    void testSearchBooks() {
        ResponseEntity<List<BookDto>> response = bookController.searchBooks("The Great Gatsby", null, null);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void testGetBookById() {
        ResponseEntity<BookDto> response = bookController.getBookById(1L);
        assertThat(response.getBody().getTitle()).isEqualTo("The Great Gatsby");
    }

    @Test
    void testGetBookReviews() {
        ResponseEntity<String> response = bookController.getBookReviews(1L);
        assertThat(response.getBody()).isEqualTo("서점 리뷰 내용 (크롤링 데이터 기반)");
    }

    @Test
    void testGetPopularBooks() {
        ResponseEntity<List<BookDto>> response = bookController.getPopularBooks();
        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    void testAddWantToRead() {
        WantToReadRequest request = new WantToReadRequest(1L, 2L);
        ResponseEntity<String> response = bookController.addWantToRead(request);
        assertThat(response.getBody()).isEqualTo("책이 읽고 싶은 목록에 추가되었습니다.");
    }

    @Test
    void testMarkBookAsRead() {
        ReadStatusUpdateRequest request = new ReadStatusUpdateRequest(1L);
        ResponseEntity<String> response = bookController.markBookAsRead(1L, request);
        assertThat(response.getBody()).isEqualTo("책이 '읽음' 상태로 업데이트되었습니다.");
    }
}
