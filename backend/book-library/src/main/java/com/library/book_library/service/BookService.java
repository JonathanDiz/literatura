package com.library.book_library.service;

import com.library.book_library.model.Book;
import com.library.book_library.model.GutendexAuthor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    public Page<Book> getBooks(PageRequest pageRequest) {
        // Simulación de datos
        GutendexAuthor author1 = new GutendexAuthor("Author One");
        GutendexAuthor author2 = new GutendexAuthor("Author Two");

        Book book1 = new Book(1, "Sample Book 1", 100, Arrays.asList("EN"), Arrays.asList(author1, author2));
        Book book2 = new Book(2, "Sample Book 2", 200, Arrays.asList("ES"), Arrays.asList(author1));

        List<Book> books = Arrays.asList(book1, book2);

        // Paginación simulada
        return new PageImpl<>(books, pageRequest, books.size());
    }

    public List<Book> searchBooks(String keyword) {
        // Simulación de búsqueda
        if (keyword.equalsIgnoreCase("sample")) {
            GutendexAuthor author1 = new GutendexAuthor("Author Example");
            Book book = new Book(1, "Example Book", 300, Collections.singletonList("EN"), Collections.singletonList(author1));
            return Collections.singletonList(book);
        }
        return Collections.emptyList();
    }
}
