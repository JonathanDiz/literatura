package com.library.book_library.service;

import com.library.book_library.model.Author;
import com.library.book_library.model.Book;
import com.library.book_library.model.GutendexResponse;
import com.library.book_library.model.GutendexBook;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final WebClient webClient;

    public BookService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<Book>> searchBooks(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("search", query).build())
                .retrieve()
                .bodyToMono(GutendexResponse.class)
                .map(response -> response.getResults()) // Obtenemos los libros de la API
                .map(this::mapToBooks);                 // Convertimos a nuestro modelo interno
    }

    private List<Book> mapToBooks(List<GutendexBook> gutendexBooks) {
        return gutendexBooks.stream()
                .map(book -> new Book(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthors().stream()
                                .map(author -> new Author(author.getName())) // Convertimos autores
                                .collect(Collectors.toList()),
                        book.getDownloadCount(),
                        book.getLanguages()
                ))
                .collect(Collectors.toList());
    }
}
