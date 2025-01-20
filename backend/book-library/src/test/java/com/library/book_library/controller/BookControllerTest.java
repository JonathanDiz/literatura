package com.library.book_library.controller;

import com.library.book_library.model.Book;
import com.library.book_library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private WebTestClient webTestClient;

    @Test
    void testSearchBooks() {
        Book book = new Book();
        book.setTitle("Test Book");

        when(bookService.searchBooks("test"))
                .thenReturn(List.of(book));

        webTestClient.get()
                .uri("/api/books/search?keyword=test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .hasSize(1);
    }
}
