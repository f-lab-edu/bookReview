package com.example.moduleservice.service;

import com.example.moduleservice.repository.BookRepository;
import com.example.modulecore.dto.BookDto;
import com.example.modulecore.dto.ReadStatusUpdateRequest;
import com.example.modulecore.dto.ReviewDto;
import com.example.modulecore.dto.WantToReadRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> searchBooks(String title, String author, String genre) {
        return bookRepository.searchBooks(title, author, genre);
    }

    public Optional<BookDto> getBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    public List<ReviewDto> getBookReviews(Long bookId) {
        return bookRepository.getBookReviews(bookId);
    }

    public BookDto addWantToRead(WantToReadRequest request) {
        return bookRepository.findBookById(request.getBookId()).orElse(null);
    }

    public BookDto markBookAsRead(Long bookId, ReadStatusUpdateRequest request) {
        return bookRepository.findBookById(bookId).orElse(null);
    }

    public List<BookDto> getPopularBooks() {
        return bookRepository.getPopularBooks();
    }
}
