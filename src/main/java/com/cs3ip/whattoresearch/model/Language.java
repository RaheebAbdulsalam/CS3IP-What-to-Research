package com.cs3ip.whattoresearch.model;

import jakarta.persistence.*;


@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String language;

    // Constructors, getters, and setters


    public Language(Integer id, String language) {
        this.id = id;
        this.language = language;
    }


    public Language() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
