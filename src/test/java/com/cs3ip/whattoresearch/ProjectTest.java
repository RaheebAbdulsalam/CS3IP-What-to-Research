package com.cs3ip.whattoresearch;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import com.cs3ip.whattoresearch.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectTest {
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectService projectService;
    private Project project1;
    private Project project2;

    @BeforeEach
    void setUp() {
        project1 = new Project();
        project1.setId(1);
        project1.setProjectTitle("Project A");

        project2 = new Project();
        project2.setId(2);
        project2.setProjectTitle("Project B");
    }

    @Test
    void testGetAllProjects() {
        // Setup
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        when(projectRepository.findAll()).thenReturn(projects);

        // Execute
        List<Project> result = projectService.getAllProjects();

        // Verify
        assertEquals(2, result.size());
        assertEquals("Project A", result.get(0).getProjectTitle());
        assertEquals("Project B", result.get(1).getProjectTitle());
    }

    @Test
    void testGetProjectById() {
        // Setup
        when(projectRepository.findById(1)).thenReturn(Optional.of(project1));

        // Execute
        Project result = projectService.getProjectById(1);

        // Verify
        assertNotNull(result);
        assertEquals("Project A", result.getProjectTitle());
    }

}
