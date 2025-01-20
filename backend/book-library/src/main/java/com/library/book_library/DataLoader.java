package com.library.book_library;

import com.library.book_library.model.Book;
import com.library.book_library.model.Format;
import com.library.book_library.model.GutendexAuthor;
import com.library.book_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear autores
        GutendexAuthor author1 = new GutendexAuthor("Author One");
        GutendexAuthor author2 = new GutendexAuthor("Author Two");

        // Crear formatos
        Format format1 = new Format("PDF", "https://example.com/book1.pdf", null);
        Format format2 = new Format("EPUB", "https://example.com/book2.epub", null);

        // Crear libros
        Book book1 = new Book(
                "Sample Book 1",
                Arrays.asList("Subject 1", "Subject 2"),
                Arrays.asList("Bookshelf 1"),
                Arrays.asList("EN"),
                Arrays.asList(true, false),
                "Text",
                100,
                300,
                Arrays.asList(format1),
                Arrays.asList(author1, author2)
        );

        Book book2 = new Book(
                "Sample Book 2",
                Arrays.asList("Subject 3"),
                Arrays.asList("Bookshelf 2"),
                Arrays.asList("ES"),
                Arrays.asList(true),
                "Audio",
                200,
                250,
                Arrays.asList(format2),
                Arrays.asList(author1)
        );

        // Establecer la relación entre formatos y libros
        format1.setBook(book1);
        format2.setBook(book2);

        // Guardar libros en la base de datos
        bookRepository.saveAll(Arrays.asList(book1, book2));

        // Imprimir libros guardados
        bookRepository.findAll().forEach(System.out::println);
    }
}
