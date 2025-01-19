package com.library.book_library.model;

import jakarta.persistence.*;

@Entity
public class GutendexAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public GutendexAuthor() {}

    public GutendexAuthor(String name) {
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

    @Override
    public String toString() {
        return "GutendexAuthor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
