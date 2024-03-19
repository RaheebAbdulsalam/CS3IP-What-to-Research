package com.cs3ip.whattoresearch.controller;


import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.service.ProjectFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/research")
public class ProjectController {

    @Autowired
    private ProjectFilterService projectFilterService;

    // Returns research form page
    @GetMapping("/research-form")
    public ModelAndView getResearchForm() {
        ModelAndView mav = new ModelAndView("/research-form");
        return mav;
    }

    @PostMapping("/search")
    public ModelAndView searchProjects(String projectMethodology, String projectType, String favLanguage, String programmingSkills) {
        List<String> favLanguages = Arrays.asList(favLanguage.split(","));
        int languageSize = favLanguages.size();
        List<Project> filteredProjects = projectFilterService.searchProjects(projectMethodology, projectType, favLanguages, languageSize, programmingSkills);
        ModelAndView modelAndView = new ModelAndView("search-results");
        modelAndView.addObject("projects", filteredProjects);
        return modelAndView;
    }

    @GetMapping("/display/{id}")
    public ModelAndView getProjectDetail(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/projectDetail");
        Project project=projectFilterService.getProjectById(id);
        mav.addObject("project", project);
        return mav;
    }

}


//    @PostMapping("/search")
//    public ModelAndView searchProjects(String projectMethodology, String projectType, String favLanguage, String programmingSkills) {
//        List<Project> filteredProjects = projectFilterService.searchProjects(projectMethodology, projectType, favLanguage, programmingSkills);
//        ModelAndView modelAndView = new ModelAndView("search-results");
//        modelAndView.addObject("projects", filteredProjects);
//        return modelAndView;
//    }

