package com.cs3ip.whattoresearch.controller;


import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.service.ProjectFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * RESTful controller for controlling research-related functionalities.
 */

@RestController
@RequestMapping("/research")
public class ProjectController {

    @Autowired
    private ProjectFilterService projectFilterService;

    /**
     * Retrieves the research form page view.
     *
     * @return A ModelAndView object for the research form page.
     */
    @GetMapping("/research-form")
    public ModelAndView getResearchForm() {
        ModelAndView mav = new ModelAndView("/research-form");
        return mav;
    }

    /**
     * Handles the search for projects based on given preferences.
     *
     * @param projectMethodology The methodology used in the projects.
     * @param projectType       The type or category of projects.
     * @param favLanguage       The favorite programming language(s) of the students, separated by commas.
     * @param programmingSkills The programming skill level of the student.
     * @return A ModelAndView object for the search results page.
     */
    @PostMapping("/search")
    public ModelAndView searchProjects(String projectMethodology, String projectType, String favLanguage, String programmingSkills) {
        List<String> favLanguages = Arrays.asList(favLanguage.split(","));
        int languageSize = favLanguages.size();
        List<Project> filteredProjects = projectFilterService.searchProjects(projectMethodology, projectType, favLanguages, languageSize, programmingSkills);
        ModelAndView modelAndView = new ModelAndView("search-results");
        modelAndView.addObject("projects", filteredProjects);
        return modelAndView;
    }

    /**
     * Retrieves the detail page of a specific project.
     *
     * @param id The id of the project.
     * @return A ModelAndView object for the project detail page.
     */
    @GetMapping("/display/{id}")
    public ModelAndView getProjectDetail(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/projectDetail");
        Project project=projectFilterService.getProjectById(id);
        mav.addObject("project", project);
        return mav;
    }

}

