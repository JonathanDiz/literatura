package com.library.book_library.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ElementCollection
    private List<String> subjects;

    @ElementCollection
    private List<String> bookshelves;

    @ElementCollection
    private List<String> languages;

    @ElementCollection
    private List<Boolean> copyright;

    private String mediaType;

    private Integer downloadCount;

    private Integer pages;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Format> formats;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<GutendexAuthor> authors;

    // Constructores, getters y setters
    public Book() {}

    public Book(String title, List<String> subjects, List<String> bookshelves, List<String> languages, List<Boolean> copyright,
                String mediaType, Integer downloadCount, Integer pages, List<Format> formats, List<GutendexAuthor> authors) {
        this.title = title;
        this.subjects = subjects;
        this.bookshelves = bookshelves;
        this.languages = languages;
        this.copyright = copyright;
        this.mediaType = mediaType;
        this.downloadCount = downloadCount;
        this.pages = pages;
        this.formats = formats;
        this.authors = authors;
    }

    // Getters y setters

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
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return List<String> return the languages
     */
    public List<String> getLanguages() {
        return languages;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(List<String> languages) {
        this.languages = languages;
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
     * @return Integer return the downloadCount
     */
    public Integer getDownloadCount() {
        return downloadCount;
    }

    /**
     * @param downloadCount the downloadCount to set
     */
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * @return Integer return the pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(Integer pages) {
        this.pages = pages;
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
     * @return List<GutendexAuthor> return the authors
     */
    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
    }

}
