package com.library.book_library.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private List<String> subjects;
    private List<Person> translators;
    private List<String> bookshelves;
    private List<Boolean> copyright;
    private String mediaType;
    private List<Format> formats;
    private int downloadCount;
    private List<String> languages;
    
    @ManyToMany(mappedBy = "books")
    private List<Genre> genres;
   
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GutendexBook> gutendexBooks;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<GutendexAuthor> authors;

    @ElementCollection
    private List<String> gutendexAuthorNames; // Solo almacena los nombres de los autores


    // Constructores
    public Book() {}

    public Book(String title, List<String> gutendexAuthorNames) {
        this.title = title;
        this.gutendexAuthorNames = gutendexAuthorNames;
    }

    public Book(int id2, String title2, List<GutendexAuthor> downloadCount, int languages, List<String> authors2) {
        //TODO Auto-generated constructor stub
    }

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public List<String> getLanguages() {
        return languages;
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

    /**
     * @return List<String> return the subjects
     */
    public List<String> getSubjects() {
        return subjects;
    }

    /**
     * @param subjects the subjects to set
     */
    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    /**
     * @return List<Person> return the translators
     */
    public List<Person> getTranslators() {
        return translators;
    }

    /**
     * @param translators the translators to set
     */
    public void setTranslators(List<Person> translators) {
        this.translators = translators;
    }

    /**
     * @return List<String> return the bookshelves
     */
    public List<String> getBookshelves() {
        return bookshelves;
    }

    /**
     * @param bookshelves the bookshelves to set
     */
    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    /**
     * @return List<Boolean> return the copyright
     */
    public List<Boolean> getCopyright() {
        return copyright;
    }

    /**
     * @param copyright the copyright to set
     */
    public void setCopyright(List<Boolean> copyright) {
        this.copyright = copyright;
    }

    /**
     * @return String return the mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * @param mediaType the mediaType to set
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * @return List<Format> return the formats
     */
    public List<Format> getFormats() {
        return formats;
    }

    /**
     * @param formats the formats to set
     */
    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    /**
     * @param downloadCount the downloadCount to set
     */
    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    /**
     * @return List<Genre> return the genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * @param genres the genres to set
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * @return List<GutendexBook> return the gutendexBooks
     */
    public List<GutendexBook> getGutendexBooks() {
        return gutendexBooks;
    }

    /**
     * @param gutendexBooks the gutendexBooks to set
     */
    public void setGutendexBooks(List<GutendexBook> gutendexBooks) {
        this.gutendexBooks = gutendexBooks;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
    }

}
