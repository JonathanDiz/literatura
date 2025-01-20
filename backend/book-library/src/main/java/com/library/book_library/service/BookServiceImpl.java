package com.library.book_library.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.book_library.model.Book;
import com.library.book_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> fetchBooksFromGutendex(int limit) {
        String apiUrl = "https://gutendex.com/books?limit=" + limit;

        // Llamada a la API de Gutendex
        String response = webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Convertir JSON a lista de objetos Book
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> books;

        try {
            books = objectMapper.readValue(
                response,
                new TypeReference<>() {}
            );

            // Guardar los libros en la base de datos
            bookRepository.saveAll(books);
        } catch (Exception e) {
            throw new RuntimeException("Error procesando la respuesta de Gutendex", e);
        }

        return books;
    }
}
