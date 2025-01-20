package com.library.book_library.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "genre_book",
        joinColumns = @JoinColumn(name = "genre_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    // Constructores, getters y setters
    public Genre() {}

    public Genre(String name) {
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
