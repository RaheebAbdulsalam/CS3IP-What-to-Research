package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProducts() {
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
        project.setMethodology(projectDetails.getMethodology());
        project.setPreferredLanguages(projectDetails.getPreferredLanguages());
        project.setProgrammingSkills(projectDetails.getProgrammingSkills());
        return projectRepository.save(project);
    }

    public void deleteProduct(Integer id) {
        projectRepository.deleteById(id);
    }
}

