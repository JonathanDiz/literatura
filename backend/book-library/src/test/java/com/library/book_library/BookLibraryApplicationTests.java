package com.library.book_library;

import com.library.book_library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class BookLibraryApplicationTests {

    @Mock
    private WebClient.Builder webClientBuilder;

    // Mockea BookService para evitar llamadas reales a la API
    @Mock
    private BookService bookService;

    @Test
    void contextLoads() {
        // Este test solo verifica si el contexto de Spring se carga correctamente
    }
}
