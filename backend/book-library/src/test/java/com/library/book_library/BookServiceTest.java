package com.library.book_library;

import com.library.book_library.model.Author;
import com.library.book_library.model.Book;
import com.library.book_library.model.GutendexResponse;
import com.library.book_library.model.GutendexBook;
import com.library.book_library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private WebClient webClient; // Simulamos WebClient

    @InjectMocks
    private BookService bookService; // Clase que estamos probando

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializamos los mocks
    }

    @Test
    void testSearchBooks() {
        // Simulamos un libro devuelto por la API
        GutendexBook gutendexBook = new GutendexBook(
                1,
                "Pride and Prejudice",
                List.of(new GutendexBook.Author("Jane Austen")),
                100000,
                List.of("en")
        );

        GutendexResponse response = new GutendexResponse();
        response.setResults(List.of(gutendexBook));

        // Mock del WebClient
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(GutendexResponse.class)).thenReturn(Mono.just(response));

        // Llamada al servicio
        List<Book> result = bookService.searchBooks("Pride and Prejudice").block();

        // Verificamos el resultado
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);

        Book book = result.get(0);
        assertThat(book.getTitle()).isEqualTo("Pride and Prejudice");
        assertThat(book.getAuthors().get(0).getName()).isEqualTo("Jane Austen");
        assertThat(book.getDownloadCount()).isEqualTo(100000);
        assertThat(book.getLanguages()).containsExactly("en");
    }
}
