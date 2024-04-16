package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.*;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for filtering and retrieving projects based on user preferences.
 */
@Service
public class ProjectFilterService {
    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Searches for projects based on the specified preferences.
     *
     * @param projectMethodology The methodology of the project.
     * @param projectType        The type of the project.
     * @param favLanguages       The favorite programming languages.
     * @param languageSize       The number of favorite programming languages.
     * @param programmingSkills  The programming skills required for the project.
     * @return A list of projects matching the specified criteria.
     */
    public List<Project> searchProjects(String projectMethodology, String projectType, List<String> favLanguages, int languageSize, String programmingSkills) {
        return projectRepository.findProjectsByPreferences(projectMethodology, projectType, favLanguages, languageSize, programmingSkills);
    }

    /**
     * Retrieves a project by ID.
     *
     * @param id The ID of the project.
     * @return The project with the ID.
     * @throws ResourceNotFoundException If no project is found with the specified ID.
     */
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

}
