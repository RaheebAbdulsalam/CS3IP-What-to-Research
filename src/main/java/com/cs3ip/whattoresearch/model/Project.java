package com.cs3ip.whattoresearch.model;


import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_title", nullable = false)
    private String projectTitle;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String methodology;

    @ManyToOne
    @JoinColumn(name = "project_type_id")
    private ProjectCategory projectCategory;

    @ManyToOne
    @JoinColumn(name = "programming_skill_id")
    private SkillLevel programmingSkill;

    @ManyToMany
    @JoinTable(
            name = "project_language",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private List<Language> preferredLanguages;


    // Getters and setters


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

    public ProjectCategory getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(ProjectCategory projectCategory) {
        this.projectCategory = projectCategory;
    }

    public SkillLevel getProgrammingSkill() {
        return programmingSkill;
    }

    public void setProgrammingSkill(SkillLevel programmingSkill) {
        this.programmingSkill = programmingSkill;
    }

    public List<Language> getPreferredLanguages() {
        return preferredLanguages;
    }

    public void setPreferredLanguages(List<Language> preferredLanguages) {
        this.preferredLanguages = preferredLanguages;
    }
}