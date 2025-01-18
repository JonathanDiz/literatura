package com.library.book_library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.book_library.model.GutendexAuthor;

public interface GutendexAuthorRepository extends JpaRepository<GutendexAuthor, Long> {
  Optional<GutendexAuthor> findByName(String name);
}