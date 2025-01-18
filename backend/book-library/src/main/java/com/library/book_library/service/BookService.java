package com.library.book_library.service;

import com.library.book_library.model.GutendexAuthor;
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

    // Constructor donde inicializamos WebClient
    public BookService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Método principal de búsqueda de libros
    public Mono<List<Book>> searchBooks(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/search")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(GutendexResponse.class)
                .map(GutendexResponse::getResults)
                .map(this::mapToBooks);
    }

    // Conversión explícita de GutendexBook a Book
    private List<Book> mapToBooks(List<GutendexBook> gutendexBooks) {
        return gutendexBooks.stream()
                .map(book -> new Book(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthors().stream()
                                .map(author -> new GutendexAuthor(author.getName())) // Convertimos autores
                                .collect(Collectors.toList()),
                        book.getDownloadCount(),
                        book.getLanguages()
                ))
                .collect(Collectors.toList());
    }
}
