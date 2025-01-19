package com.library.book_library.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
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
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@ExtendWith(MockitoExtension.class)
public class BookServiceWireMockTest {

    private static final String SEARCH_ENDPOINT = "/search";
    private static WireMockServer wireMockServer;

    private AutoCloseable mocks;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private GutendexAuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeAll
    static void setUpWireMockServer() {
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        
        WebClient webClient = WebClient.builder()
            .baseUrl(String.format("http://localhost:%d", wireMockServer.port()))
            .build();
        
        bookService = new BookService(webClient, bookRepository, authorRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close();
        }
        wireMockServer.resetAll();
    }

    @AfterAll
    static void tearDownWireMockServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    @Test
    void searchBooks_Success() {
        // Configurar el stub antes de la prueba
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
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(mockResponse)));

        // Ejecutar la prueba
        Mono<List<Book>> result = bookService.searchBooks("test");

        // Verificar el resultado
        StepVerifier.create(result)
            .expectNextMatches(books -> {
                if (books.isEmpty()) return false;
                Book book = books.get(0);
                return book.getId() == 1 &&
                       "Test Book".equals(book.getTitle()) &&
                       book.getDownloadCount() == 100 &&
                       book.getLanguages().containsAll(List.of("en", "es")) &&
                       !book.getAuthors().isEmpty() &&
                       "Test Author".equals(book.getAuthors().get(0).getName());
            })
            .verifyComplete();

        // Verificar que se hizo la llamada correcta
        wireMockServer.verify(getRequestedFor(urlPathEqualTo(SEARCH_ENDPOINT))
            .withQueryParam("query", equalTo("test")));
    }
}