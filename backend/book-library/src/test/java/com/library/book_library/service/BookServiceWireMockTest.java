package com.library.book_library.service;

import com.library.book_library.model.Book;
import com.library.book_library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testSearchBooks() {
        MockitoAnnotations.openMocks(this);
        Book book = new Book();
        book.setTitle("Test Book");

        when(bookRepository.findByTitleContainingIgnoreCase("test"))
                .thenReturn(List.of(book));

        List<Book> result = bookService.searchBooks("test");

        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
    }
}
