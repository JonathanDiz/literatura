package com.library.book_library.repository;

import com.library.book_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findBySearchQuery(String searchQuery);

    Page<Book> findBySearchQuery(String searchQuery, Pageable pageable);
}
