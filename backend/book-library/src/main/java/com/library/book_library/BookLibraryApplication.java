package com.library.book_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.library.book_library.model")
@EnableJpaRepositories("com.library.book_library.repository")
public class BookLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookLibraryApplication.class, args);
    }
}