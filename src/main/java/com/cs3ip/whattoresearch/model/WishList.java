package com.cs3ip.whattoresearch.model;


import jakarta.persistence.*;

@Entity
@Table(name = "wish-list")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @JoinColumn(name = "user_id")
    private Integer userId;


    public WishList() {}

    public WishList(Integer userId, Project project) {
        this.userId = userId;
        this.project=project;
    }

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
