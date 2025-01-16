package com.library.book_library.model;

import java.util.List;

public class GutendexResponse {

    private List<GutendexBook> results;  // Lista de libros

    // Otros posibles campos como metadatos
    private int totalBooks;
    private int currentPage;

    // Getters y setters
    public List<GutendexBook> getResults() {
        return results;
    }

    public void setResults(List<GutendexBook> results) {
        this.results = results;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

