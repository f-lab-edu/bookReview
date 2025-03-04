package com.example.moduleservice.repository;

import com.example.modulecore.dto.BookDto;
import com.example.modulecore.dto.ReviewDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {
    private final Map<Long, BookDto> books = new HashMap<>();
    private final Map<Long, List<ReviewDto>> bookReviews = new HashMap<>();

    public BookRepository() {
        // 초기 더미 데이터 추가
        books.put(1L, new BookDto(1L, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.5));
        books.put(2L, new BookDto(2L, "1984", "George Orwell", "Dystopian", 4.7));
        books.put(3L, new BookDto(3L, "To Kill a Mockingbird", "Harper Lee", "Fiction", 4.8));

        bookReviews.put(1L, List.of(
                new ReviewDto(1L, 1L, 1L, "Amazing book!", "A must-read classic", 4.8),
                new ReviewDto(2L, 1L, 2L, "Very well written", "Captivating story", 4.5)
        ));
    }

    public List<BookDto> searchBooks(String title, String author, String genre) {
        return new ArrayList<>(books.values());
    }

    public Optional<BookDto> findBookById(Long bookId) {
        return Optional.ofNullable(books.get(bookId));
    }

    public List<ReviewDto> getBookReviews(Long bookId) {
        return bookReviews.getOrDefault(bookId, new ArrayList<>());
    }

    public List<BookDto> getPopularBooks() {
        return new ArrayList<>(books.values());
    }

    public void saveBook(BookDto book) {
        books.put(book.getId(), book);
    }
}
