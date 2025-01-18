package com.library.book_library.repository;

import com.library.book_library.model.Book;
import com.library.book_library.model.GutendexAuthor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        // Limpiar el repositorio antes de cada prueba
        bookRepository.deleteAll();

        // Insertar datos de prueba
        GutendexAuthor author1 = new GutendexAuthor("Author One");
        GutendexAuthor author2 = new GutendexAuthor("Author Two");

        Book book1 = new Book(1, "Java Basics", List.of(author1), 100, List.of("en"));
        Book book2 = new Book(2, "Spring Boot in Action", List.of(author2), 200, List.of("en", "es"));
        Book book3 = new Book(3, "Reactive Programming with Java", List.of(author1, author2), 150, List.of("en"));

        bookRepository.saveAll(List.of(book1, book2, book3));
    }

    @Test
    void testFindBySearchQuery() {
        // Buscar libros que coincidan con un query
        Page<Book> result = bookRepository.findBySearchQuery("Java", null);
        assertThat(result).hasSize(2); // Hay dos libros con "Java" en el título
    }

    @Test
    void testFindBySearchQueryWithPagination() {
        // Buscar libros con paginación
        Page<Book> result = bookRepository.findBySearchQuery("Java", PageRequest.of(0, 1));

        assertThat(result.getTotalElements()).isEqualTo(2); // Total de resultados es 2
        assertThat(result.getContent()).hasSize(1); // Solo se devuelve 1 resultado por página
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Java Basics");
    }
}
