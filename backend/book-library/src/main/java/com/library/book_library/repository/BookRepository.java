package com.library.book_library.repository;

import com.library.book_library.model.Book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Book> findBySearchQuery(String query, Pageable pageable);

    List<Book> findByTitleContaining(String searchQuery);
}
