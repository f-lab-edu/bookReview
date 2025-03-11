package com.example.moduleservice.repository;

import com.example.modulecore.dto.BookDto;
import com.example.modulecore.dto.ReviewDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
public class BookRepository {
//    private final Map<Long, BookDto> books = new HashMap<>();
//    private final Map<Long, List<ReviewDto>> bookReviews = new HashMap<>();
    private final ConcurrentHashMap<Long, BookDto> books = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, CopyOnWriteArrayList<ReviewDto>> bookReviews = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public BookRepository() {
        // 초기 더미 데이터 추가
        books.put(1L, new BookDto(1L, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 4.5));
        books.put(2L, new BookDto(2L, "1984", "George Orwell", "Dystopian", 4.7));
        books.put(3L, new BookDto(3L, "To Kill a Mockingbird", "Harper Lee", "Fiction", 4.8));

        bookReviews.put(1L, new CopyOnWriteArrayList<>(List.of(
                new ReviewDto(1L, 1L, 1L, "Amazing book!", "A must-read classic", 4.8),
                new ReviewDto(2L, 1L, 2L, "Very well written", "Captivating story", 4.5)
        )));
    }

    public List<BookDto> searchBooks(String title, String author, String genre) {
        lock.readLock().lock();
        try {
            return List.copyOf(books.values());
        } finally {
            lock.readLock().unlock();
        }
    }

    public Optional<BookDto> findBookById(Long bookId) {
        lock.readLock().lock();
        try {
            return Optional.ofNullable(books.get(bookId));
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<ReviewDto> getBookReviews(Long bookId) {
        return bookReviews.getOrDefault(bookId, new CopyOnWriteArrayList<>());
    }

    public List<BookDto> getPopularBooks() {
        lock.readLock().lock();
        try {
            return List.copyOf(books.values());
        } finally {
            lock.readLock().unlock();
        }
    }

    public void saveBook(BookDto book) {

        lock.writeLock().lock();
        try {
            books.put(book.getId(), book);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
