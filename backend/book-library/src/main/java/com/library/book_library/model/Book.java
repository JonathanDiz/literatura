package com.library.book_library.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private List<String> subjects;
    private List<Person> translators;
    private List<String> bookshelves;
    private List<Boolean> copyright;
    private String mediaType;
    private List<Format> formats;
    private int downloadCount;
    private Integer pages;

    @ManyToMany(mappedBy = "books")
    private List<Genre> genres;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GutendexBook> gutendexBooks;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<GutendexAuthor> authors;

    @ElementCollection
    private List<String> gutendexAuthorNames; // Solo almacena los nombres de los autores

    @ElementCollection
    private List<String> languages;

    // Constructores
    public Book() {}

    public Book(String title, List<String> gutendexAuthorNames) {
        this.title = title;
        this.gutendexAuthorNames = gutendexAuthorNames;
    }

    public Book(int id, String title, int downloadCount, List<String> languages, List<GutendexAuthor> authors) {
        this.id = id;
        this.title = title;
        this.downloadCount = downloadCount;
        this.languages = languages;
        this.authors = authors;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
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
