package com.library.book_library;

import com.library.book_library.model.GutendexBook;
import com.library.book_library.service.BookService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
void testSearchBooks() {
    GutendexBook mockGutendexBook = new GutendexBook(
            1,
            "Pride and Prejudice",
            List.of(new GutendexBook.Author("Jane Austen")),
            100000,
            List.of("en")
    );

    // Mockear el servicio para devolver una lista válida
    when(bookService.searchBooks("Pride and Prejudice"))
            .thenReturn(Mono.just(List.of(mockGutendexBook)));

    ResponseEntity<List<GutendexBook>> response = restTemplate.exchange(
            "/api/books/search?query=Pride and Prejudice",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<GutendexBook>>() {}
    );

    // Validar que la respuesta no sea nula
    assertThat(response).isNotNull();

    // Validar el cuerpo de la respuesta antes de acceder a él
    List<GutendexBook> books = response.getBody();
    assertThat(books).isNotNull().isNotEmpty();

    // Validar datos específicos en la lista
    assertThat(books.get(0).getTitle()).isEqualTo("Pride and Prejudice");
}


    @Test
    void testSearchBooksWithNullResponse() {
        // Simula una respuesta nula
        when(bookService.searchBooks("Unknown Book"))
                .thenReturn(Mono.just(List.of())); // O `Mono.empty()` si necesitas manejarlo.

        ResponseEntity<List<GutendexBook>> response = restTemplate.exchange(
                "/api/books/search?query=Unknown Book",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GutendexBook>>() {}
        );

        // Verifica que la respuesta sea válida pero no contenga datos
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isEmpty();
    }

    @Test
    void testSearchBooksWithNullServiceResponse() {
        // Simula que el servicio devuelve `null`
        when(bookService.searchBooks("Broken Service"))
                .thenReturn(null);

        ResponseEntity<List<GutendexBook>> response = restTemplate.exchange(
                "/api/books/search?query=Broken Service",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GutendexBook>>() {}
        );

        // Maneja la posible respuesta nula de forma segura
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR); // O según tu implementación.
    }
}
