package com.cs3ip.whattoresearch.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String projectTitle;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String methodology;

    @Column(nullable = false)
    private String projectType;

    @ElementCollection
    @CollectionTable(name = "preferred_languages", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "language")
    private List<String> preferredLanguages;

    @Column(nullable = false)
    private String programmingSkills;

    public Project(Integer id, String projectTitle, String description, String methodology, String projectType, List<String> preferredLanguages, String programmingSkills) {
        this.id = id;
        this.projectTitle = projectTitle;
        this.description = description;
        this.methodology = methodology;
        this.projectType = projectType;
        this.preferredLanguages = preferredLanguages;
        this.programmingSkills = programmingSkills;
    }

    public Project() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public List<String> getPreferredLanguages() {
        return preferredLanguages;
    }

    public void setPreferredLanguages(List<String> preferredLanguages) {
        this.preferredLanguages = preferredLanguages;
    }

    public String getProgrammingSkills() {
        return programmingSkills;
    }

    public void setProgrammingSkills(String programmingSkills) {
        this.programmingSkills = programmingSkills;
    }
}
