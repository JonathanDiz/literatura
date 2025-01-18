package com.library.book_library.repository;

import com.library.book_library.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBySearchQuery(String query);

    List<Book> findByTitleContaining(String searchQuery);
}
