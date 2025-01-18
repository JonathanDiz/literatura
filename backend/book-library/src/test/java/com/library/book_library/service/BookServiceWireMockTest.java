package com.library.book_library.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.library.book_library.model.Book;
import com.library.book_library.repository.BookRepository;
import com.library.book_library.repository.GutendexAuthorRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceWireMockTest {

    private static final String SEARCH_ENDPOINT = "/search";
    private static WireMockServer wireMockServer;

    private AutoCloseable mocks; // Para cerrar los mocks automáticamente

    @Mock
    private BookRepository bookRepository;

    @Mock
    private GutendexAuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeAll
    static void setUpWireMockServer() {
        wireMockServer = new WireMockServer(0); // Puerto dinámico
        wireMockServer.start();
    }

    @BeforeEach
    void setUp() {
        // Inicializa los mocks de Mockito
        mocks = MockitoAnnotations.openMocks(this);

        // Configura el WebClient con la URL dinámica del servidor WireMock
        String baseUrl = "http://localhost:" + wireMockServer.port();
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        // Crea manualmente una instancia de BookService con los mocks
        bookService = new BookService(webClient, bookRepository, authorRepository);
    }

    @AfterEach
    void tearDownMocks() throws Exception {
        // Cierra los mocks para liberar recursos
        if (mocks != null) {
            mocks.close();
        }
    }

    @AfterAll
    static void tearDownWireMockServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @Test
    void searchBooks_Success() {
        String mockResponse = """
            {
                "results": [
                    {
                        "id": 1,
                        "title": "Test Book",
                        "authors": [
                            { "name": "Test Author" }
                        ],
                        "download_count": 100,
                        "languages": ["en", "es"]
                    }
                ]
            }
            """;

        wireMockServer.stubFor(get(urlPathEqualTo(SEARCH_ENDPOINT))
                .withQueryParam("query", equalTo("test"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(mockResponse)
                ));

        Mono<List<Book>> result = bookService.searchBooks("test");

        StepVerifier.create(result)
                .expectNextMatches(books -> {
                    Book book = books.get(0);
                    return book.getId() == 1 &&
                            book.getTitle().equals("Test Book") &&
                            book.getDownloadCount() == 100 &&
                            book.getLanguages().containsAll(List.of("en", "es")) &&
                            book.getAuthors().get(0).getName().equals("Test Author");
                })
                .verifyComplete();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(SEARCH_ENDPOINT))
                .withQueryParam("query", equalTo("test")));
    }
}
