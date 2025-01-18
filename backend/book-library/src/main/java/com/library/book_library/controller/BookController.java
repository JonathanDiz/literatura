package com.library.book_library.controller;

import com.library.book_library.dto.PaginatedResponse;
import com.library.book_library.model.Book;
import com.library.book_library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.library.book_library.dto.PaginatedResponse;
import com.library.book_library.model.Book;
import com.library.book_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public PaginatedResponse<Book> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Book> bookPage = bookService.getBooks(PageRequest.of(page, size));

        return new PaginatedResponse<>(
                bookPage.getTotalElements(),
                bookPage.hasNext() ? "/books?page=" + (page + 1) + "&size=" + size : null,
                bookPage.hasPrevious() ? "/books?page=" + (page - 1) + "&size=" + size : null,
                bookPage.getContent()
        );
    }

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
