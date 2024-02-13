package com.cs3ip.whattoresearch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project_category")
public class ProjectCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String category;

    // Constructors, getters, and setters
    public ProjectCategory(Integer id, String category) {
        this.id = id;
        this.category = category;
    }


    public ProjectCategory() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
