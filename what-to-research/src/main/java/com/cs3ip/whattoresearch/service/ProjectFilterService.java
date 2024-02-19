package com.cs3ip.whattoresearch.service;


import com.cs3ip.whattoresearch.model.*;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectFilterService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> filterProjects(Project project) {
        List<Project> allProjects = projectRepository.findAll();

        // Perform filtering based on the submitted criteria
        return allProjects.stream()
                .filter(p -> filterByMethodology(p, project.getProjectMethodology()))
                .filter(p -> filterByTypes(p, project.getTypes()))
                .filter(p -> filterByProgrammingSkill(p, project.getProgrammingSkill()))
                .filter(p -> filterByLanguages(p, project.getPreferredLanguages()))
                .collect(Collectors.toList());
    }

    private boolean filterByMethodology(Project project, Methodology methodology) {
        return methodology == null || project.getProjectMethodology().equals(methodology);
    }

    private boolean filterByTypes(Project project, List<Type> types) {
        return types == null || types.isEmpty() || project.getTypes().containsAll(types);
    }

    private boolean filterByProgrammingSkill(Project project, SkillLevel programmingSkill) {
        return programmingSkill == null || project.getProgrammingSkill().equals(programmingSkill);
    }

    private boolean filterByLanguages(Project project, List<Language> languages) {
        return languages == null || languages.isEmpty() || project.getPreferredLanguages().containsAll(languages);
    }
}
