package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Integer id, Project projectDetails) {
        Project project = getProjectById(id);
        project.setProjectTitle(projectDetails.getProjectTitle());
        project.setDescription(projectDetails.getDescription());
        project.setTypes(projectDetails.getTypes());
        project.setProjectMethodology(projectDetails.getProjectMethodology());
        project.setPreferredLanguages(projectDetails.getPreferredLanguages());
        project.setProgrammingSkill(projectDetails.getProgrammingSkill());
        return projectRepository.save(project);
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

}

