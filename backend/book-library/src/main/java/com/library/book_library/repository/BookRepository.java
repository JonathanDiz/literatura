package com.library.book_library.repository;

import com.library.book_library.model.Book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findBySearchQuery(String searchQuery, PageRequest pageRequest);

    List<Book> findByTitleContaining(String searchQuery);
}
