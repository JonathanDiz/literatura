package com.library.book_library.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class GutendexAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    // Constructores, getters y setters
    public GutendexAuthor() {}

    public GutendexAuthor(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
