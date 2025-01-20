package com.library.book_library.model;

import jakarta.persistence.*;

@Entity
public class Format {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String formatType;

    private String url;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Constructores, getters y setters
    public Format() {}

    public Format(String formatType, String url, Book book) {
        this.formatType = formatType;
        this.url = url;
        this.book = book;
    }

    // Getters y setters omitidos por brevedad

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return String return the formatType
     */
    public String getFormatType() {
        return formatType;
    }

    /**
     * @param formatType the formatType to set
     */
    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    /**
     * @return String return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return Book return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

}
