package com.library.book_library.controller;

import com.library.book_library.model.Book;
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

    /**
     * Endpoint para buscar libros en Gutendex.
     *
     * @param query Cadena de búsqueda proporcionada por el usuario.
     * @return Lista reactiva de libros convertidos al modelo interno `Book`.
     */
    @GetMapping("/search")
    public Mono<List<Book>> searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
}
