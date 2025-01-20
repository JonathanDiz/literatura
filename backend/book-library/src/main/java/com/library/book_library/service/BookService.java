package com.library.book_library.service;

import com.library.book_library.model.Book;
import java.util.List;

public interface BookService {
    List<Book> findBooksByTitle(String title);
    List<Book> findAllBooks();
    List<Book> fetchBooksFromGutendex(int limit);
}
