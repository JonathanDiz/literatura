package com.library.book_library.controller;

import com.library.book_library.model.Book;
import com.library.book_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GutendexController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/load-books")
    public ResponseEntity<List<Book>> loadBooksFromGutendex(@RequestParam(defaultValue = "10") int limit) {
        List<Book> books = bookService.fetchBooksFromGutendex(limit);
        return ResponseEntity.ok(books);
    }
}
