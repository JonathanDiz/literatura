package com.library.book_library.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ElementCollection
    private List<String> gutendexAuthorNames; // Solo almacena los nombres de los autores

    @OneToMany(mappedBy = "Book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GutendexBook> gutendexBooks;

    // Constructores
    public Book() {}

    public Book(String title, List<String> gutendexAuthorNames) {
        this.title = title;
        this.gutendexAuthorNames = gutendexAuthorNames;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGutendexAuthorNames() {
        return gutendexAuthorNames;
    }

    public void setGutendexAuthorNames(List<String> gutendexAuthorNames) {
        this.gutendexAuthorNames = gutendexAuthorNames;
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", gutendexAuthorNames=" + gutendexAuthorNames +
               '}';
    }
}
