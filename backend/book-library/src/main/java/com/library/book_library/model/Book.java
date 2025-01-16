package com.library.book_library.model;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private List<Author> authors;
    private int downloadCount;
    private List<String> languages;

    public Book(int id, String title, List<Author> authors, int downloadCount, List<String> languages) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.downloadCount = downloadCount;
        this.languages = languages;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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
}
