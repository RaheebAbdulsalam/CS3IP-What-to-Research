package com.cs3ip.whattoresearch.service;


import com.cs3ip.whattoresearch.model.*;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectFilterService {

    @Autowired
    private ProjectRepository projectRepository;

//    public List<Project> searchProjects(String projectMethodology, String projectType, String favLanguage, String programmingSkills) {
//        return projectRepository.findProjectsByPreferences(projectMethodology, projectType, favLanguage, programmingSkills);
//    }

    public List<Project> searchProjects(String projectMethodology, String projectType, List<String> favLanguages, int languageSize, String programmingSkills) {
        return projectRepository.findProjectsByPreferences(projectMethodology, projectType, favLanguages, languageSize, programmingSkills);
    }



}
