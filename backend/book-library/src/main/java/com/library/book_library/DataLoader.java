package com.library.book_library;

import com.library.book_library.model.Book;
import com.library.book_library.model.GutendexAuthor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Crear autores
        GutendexAuthor author1 = new GutendexAuthor("Author One");
        GutendexAuthor author2 = new GutendexAuthor("Author Two");

        // Crear libros
        Book book1 = new Book(1, "Sample Book 1", 100, Arrays.asList("EN"), Arrays.asList(author1, author2));
        Book book2 = new Book(2, "Sample Book 2", 200, Arrays.asList("ES"), Arrays.asList(author1));

        System.out.println(book1);
        System.out.println(book2);
    }
}
