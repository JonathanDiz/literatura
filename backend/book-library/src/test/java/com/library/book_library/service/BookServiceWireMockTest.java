package com.library.book_library.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.library.book_library.model.Book;
import com.library.book_library.repository.BookRepository;
import com.library.book_library.repository.GutendexAuthorRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BookServiceWireMockTest {

    private static final String SEARCH_ENDPOINT = "/search";
    private static WireMockServer wireMockServer;

    @Mock
    private static BookRepository bookRepository;
    
        @Mock
        private static GutendexAuthorRepository authorRepository;
            
                @Autowired
                private static BookService bookService;
                
                    @BeforeAll
                    static void setUp() {
                        wireMockServer = new WireMockServer(0); // Puerto dinámico
                        wireMockServer.start();
                
                        String baseUrl = "http://localhost:" + wireMockServer.port();
                        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
                        bookService = new BookService(webClient, bookRepository, authorRepository);
    }

    @AfterAll
    static void tearDown() {
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

    @Test
    void searchBooks_EmptyResponse() {
        String mockResponse = """
            { "results": [] }
            """;

        wireMockServer.stubFor(get(urlPathEqualTo(SEARCH_ENDPOINT))
                .withQueryParam("query", equalTo("empty"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(mockResponse)
                ));

        Mono<List<Book>> result = bookService.searchBooks("empty");

        StepVerifier.create(result)
                .expectNextMatches(List::isEmpty)
                .verifyComplete();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(SEARCH_ENDPOINT))
                .withQueryParam("query", equalTo("empty")));
    }

    @Test
    void searchBooks_ErrorResponse() {
        wireMockServer.stubFor(get(urlPathEqualTo(SEARCH_ENDPOINT))
                .withQueryParam("query", equalTo("error"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\": \"Internal Server Error\"}")
                ));

        Mono<List<Book>> result = bookService.searchBooks("error");

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("500"))
                .verify();

        wireMockServer.verify(getRequestedFor(urlPathEqualTo(SEARCH_ENDPOINT))
                .withQueryParam("query", equalTo("error")));
    }
}
