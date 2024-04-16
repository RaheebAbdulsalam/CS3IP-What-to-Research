package com.cs3ip.whattoresearch.model;


import jakarta.persistence.*;

/**
 * The Entity class representing a user shortlist in the application.
 */

@Entity
@Table(name = "short-list")
public class ShortList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @JoinColumn(name = "user_id")
    private Integer userId;

    public ShortList() {}

    public ShortList(Integer userId, Project project) {
        this.userId = userId;
        this.project=project;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
