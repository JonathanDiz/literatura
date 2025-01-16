package com.library.book_library;

import com.library.book_library.model.Book;
import com.library.book_library.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") // Usa un perfil de prueba para configuraciones aisladas
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        // Preparamos los datos de prueba
        book = Book.builder()
                .gutendexId("1")
                .title("Pride and Prejudice")
                .author("Jane Austen")
                .downloadCount("100000")
                .languages("en")
                .build();
    }

    @Test
    void testSaveBook() {
        // Guardamos el libro
        Book savedBook = bookRepository.save(book);

        // Verificamos que el libro se guardó correctamente
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Pride and Prejudice");
    }

    @Test
    void testFindAllBooks() {
        // Guardamos varios libros
        bookRepository.save(book);
        Book secondBook = Book.builder()
                .gutendexId("2")
                .title("Sense and Sensibility")
                .author("Jane Austen")
                .downloadCount("200000")
                .languages("en")
                .build();
        bookRepository.save(secondBook);

        // Recuperamos todos los libros
        List<Book> books = bookRepository.findAll();

        // Verificamos que hay al menos 2 libros
        assertThat(books).hasSize(2);
    }
}
