package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing projects.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Retrieves a list of all projects.
     *
     * @return A list of all projects.
     */
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
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

    /**
     * Creates a new project.
     *
     * @param project The project to be created.
     * @return The created project.
     */
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Updates an existing project.
     *
     * @param id            The ID of the project to update.
     * @param projectDetails The updated project details.
     * @return The updated project.
     */
    public Project updateProject(Integer id, Project projectDetails) {
        Project project = getProjectById(id);
        project.setProjectTitle(projectDetails.getProjectTitle());
        project.setDescription(projectDetails.getDescription());
        project.setTypes(projectDetails.getTypes());
        project.setProjectMethodology(projectDetails.getProjectMethodology());
        project.setPreferredLanguages(projectDetails.getPreferredLanguages());
        project.setProgrammingSkill(projectDetails.getProgrammingSkill());
        project.setSupervisor(projectDetails.getSupervisor());
        return projectRepository.save(project);
    }

    /**
     * Deletes a project by ID.
     *
     * @param id The ID of the project to delete.
     */
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

}

