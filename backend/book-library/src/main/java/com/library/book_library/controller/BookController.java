package com.library.book_library.controller;

import com.library.book_library.model.GutendexBook;
import com.library.book_library.service.BookService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public Mono<List<GutendexBook>> searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
}
