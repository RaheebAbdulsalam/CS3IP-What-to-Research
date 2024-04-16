package com.cs3ip.whattoresearch.model;

import jakarta.persistence.*;

/**
 * The Entity class representing a project methodology in the application.
 */
@Entity
@Table(name = "project_methodology")
public class Methodology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "methodology_type", nullable = false)
    private String methodology_type;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethodology_type() {
        return methodology_type;
    }

    public void setMethodology_type(String methodology_type) {
        this.methodology_type = methodology_type;
    }
}
