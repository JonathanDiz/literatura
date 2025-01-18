package com.library.book_library.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GutendexBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private List<GutendexAuthor> authors;
    private int downloadCount;
    private List<String> languages;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Constructor con todos los parámetros
    public GutendexBook(int id, String title, List<GutendexAuthor> authors, int downloadCount, List<String> languages) {
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

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<GutendexAuthor> authors) {
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
