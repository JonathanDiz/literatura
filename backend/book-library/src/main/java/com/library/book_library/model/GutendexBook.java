package com.library.book_library.model;

import java.util.List;

public class GutendexBook {
    private final int id;
    private final String title;
    private final List<Author> authors;
    private final int downloadCount;
    private final List<String> languages;

    public GutendexBook(int id, String title, List<Author> authors, int downloadCount, List<String> languages) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.downloadCount = downloadCount;
        this.languages = languages;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public List<String> getLanguages() {
        return languages;
    }

    // Clase interna Author
    public static class Author {
        private final String name;

        public Author(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
