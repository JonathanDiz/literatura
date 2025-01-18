package com.library.book_library.controller;

import com.library.book_library.model.Book;
import com.library.book_library.model.GutendexAuthor;
import com.library.book_library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(BookController.class) // Anotación para pruebas específicas del controlador
public class BookControllerTest {

    @Mock
    private BookService bookService; // Mock para BookService

    @InjectMocks
    private BookController bookController; // Inyección de dependencias para el controlador

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicialización de mocks con Mockito

        // Inicializamos WebTestClient con el controlador
        webTestClient = WebTestClient.bindToController(bookController).build();
    }
    @Test
    void testSearchBooks() {
        // Crear los datos de prueba con GutendexAuthor en lugar de String
        GutendexAuthor author = new GutendexAuthor("Test Author");
        Book book1 = new Book(1, "Test Book 1", Arrays.asList(author), 100, Arrays.asList("en"));
        Book book2 = new Book(2, "Test Book 2", Arrays.asList(author), 200, Arrays.asList("es"));

        // Simular la respuesta del servicio
        when(bookService.searchBooks("test"))
                .thenReturn(Mono.just(Arrays.asList(book1, book2)));

        // Llamada al controlador
        Mono<List<Book>> result = bookController.searchBooks("test");

        // Verificación
        StepVerifier.create(result)
                .expectNextMatches(books -> {
                    Book b1 = books.get(0);
                    return b1.getId() == 1 && b1.getTitle().equals("Test Book 1") &&
                            b1.getAuthors().get(0).getName().equals("Test Author") &&
                            b1.getDownloadCount() == 100 &&
                            b1.getLanguages().contains("en") &&
                            books.size() == 2;
                })
                .verifyComplete();
    }

    @Test
    void testSearchBooks_EmptyResponse() {
        // Configurar el mock para devolver una lista vacía
        when(bookService.searchBooks("empty")).thenReturn(Mono.just(List.of()));

        // Realizar la solicitud al controlador
        webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/api/books/search")
                    .queryParam("query", "empty")
                    .build())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(Book.class)
            .hasSize(0); // Verificar que la respuesta está vacía
    }

    @Test
    void testSearchBooks_Error() {
        // Configurar el mock para devolver un error
        when(bookService.searchBooks("error")).thenReturn(Mono.error(new RuntimeException("Internal Error")));

        // Realizar la solicitud al controlador
        webTestClient.get()
            .uri(uriBuilder -> uriBuilder.path("/api/books/search")
                    .queryParam("query", "error")
                    .build())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().is5xxServerError() // Verificar código de error
            .expectBody()
            .jsonPath("$.message").isEqualTo("Internal Error"); // Verificar mensaje de error
    }
}
