package com.cs3ip.whattoresearch.model;

import jakarta.persistence.*;

/**
 * The Entity class representing a programming skill level in the application.
 */
@Entity
@Table(name = "skill_level")
public class SkillLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_level", nullable = false)
    private String skillLevel;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
