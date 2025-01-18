package com.library.book_library.service;

import com.library.book_library.model.GutendexAuthor;
import com.library.book_library.model.Book;
import com.library.book_library.model.GutendexResponse;
import com.library.book_library.repository.BookRepository;
import com.library.book_library.repository.GutendexAuthorRepository;
import com.library.book_library.model.GutendexBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final WebClient webClient;
    private final BookRepository bookRepository;  // El repositorio para manejar libros
    private final GutendexAuthorRepository authorRepository;  // Repositorio para manejar autores

    @Autowired
    public BookService(WebClient webClient, BookRepository bookRepository, GutendexAuthorRepository authorRepository) {
        this.webClient = webClient;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
                .map(book -> {
                    // Convertimos los autores a objetos persistentes
                    List<GutendexAuthor> authors = book.getAuthors().stream()
                            .map(author -> {
                                // Buscar o guardar el autor
                                GutendexAuthor gutendexAuthor = authorRepository.findByName(author.getName())
                                        .orElseGet(() -> authorRepository.save(new GutendexAuthor(author.getName())));
                                return gutendexAuthor;
                            })
                            .collect(Collectors.toList());

                    // Crear el libro con los autores ya guardados
                    Book newBook = new Book(book.getId(), book.getTitle(), book.getDownloadCount(), book.getLanguages(), authors);
                    return bookRepository.save(newBook);  // Guardamos el libro en la base de datos
                })
                .collect(Collectors.toList());
    }
}

