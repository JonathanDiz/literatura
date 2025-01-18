package com.library.book_library;

import com.library.book_library.model.Book;
import com.library.book_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
  @Autowired
  private BookRepository bookRepository;

  @Override
  public void run(String... args) {
      Book book = new Book("Java Fundamentals", Arrays.asList("John Doe", "Jane Smith"));
      bookRepository.save(book);

      bookRepository.findAll().forEach(System.out::println);
  }
}
