package com.cs3ip.whattoresearch.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name="methodology_id")
    private Methodology projectMethodology;

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


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinTable(
            name = "project_type",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id"))
    private List<Type> types;


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

    public Methodology getProjectMethodology() {
        return projectMethodology;
    }

    public void setProjectMethodology(Methodology projectMethodology) {
        this.projectMethodology = projectMethodology;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
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