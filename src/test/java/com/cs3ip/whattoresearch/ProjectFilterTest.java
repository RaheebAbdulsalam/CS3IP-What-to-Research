package com.cs3ip.whattoresearch;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import com.cs3ip.whattoresearch.service.ProjectFilterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectFilterTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectFilterService projectFilterService;

    @Test
    public void testSearchProjects() {
        String projectMethodology = "Software Development";
        String projectType = "Mobile App Development";
        List<String> favLanguages = new ArrayList<>();
        favLanguages.add("Java");
        int languageSize = 1;
        String programmingSkills = "Intermediate";

        List<Project> mockProjects = new ArrayList<>();
        mockProjects.add(new Project());
        mockProjects.add(new Project());

        when(projectRepository.findProjectsByPreferences(projectMethodology, projectType, favLanguages, languageSize, programmingSkills))
                .thenReturn(mockProjects);

        List<Project> result = projectFilterService.searchProjects(projectMethodology, projectType, favLanguages, languageSize, programmingSkills);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetProjectById() {
        Integer projectId = 1;
        Project mockProject = new Project();
        mockProject.setId(projectId);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(mockProject));

        Project result = projectFilterService.getProjectById(projectId);

        assertNotNull(result);
        assertEquals(projectId, result.getId());
    }

}

