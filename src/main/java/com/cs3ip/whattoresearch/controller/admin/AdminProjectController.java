package com.cs3ip.whattoresearch.controller.admin;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


/**
 * RESTful Controller for controlling projects.
 */
@RestController
@RequestMapping("/admin/projects")
public class AdminProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectTypeService projectTypeService;
    @Autowired
    private ProjectMethodologyService projectMethodologyService;
    @Autowired
    private ProjectLanguageService projectLanguageService;
    @Autowired
    private SkillLevelService skillLevelService;
    @Autowired
    private SupervisorService supervisorService;

    /**
     * Retrieves the admin projects page displaying a list of projects.
     *
     * @return A ModelAndView object for the admin projects page view.
     */

    @GetMapping
    public ModelAndView getAdminProjectPage() {
        ModelAndView mav = new ModelAndView("admin/projects");
        List<Project> projects = projectService.getAllProjects();
        mav.addObject("projects", projects);
        return mav;
    }

    /**
     * Retrieves all projects.
     *
     * @return A ResponseEntity containing a list of projects.
     */
    @GetMapping("/list")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    /**
     * Retrieves the page for adding a new project.
     *
     * @return A ModelAndView object for the add project page view.
     */
    @GetMapping("/create")
    public ModelAndView getAddProjectPage() {
        ModelAndView mav = new ModelAndView("admin/addProject");
        mav.addObject("project", new Project());
        mav.addObject("allTypes", projectTypeService.getAllTypes());
        mav.addObject("allMethodologies",projectMethodologyService.getAllMethodologies());
        mav.addObject("allLanguages", projectLanguageService.getAllLanguages());
        mav.addObject("allLevels",skillLevelService.getAllLevels());
        mav.addObject("supervisors",supervisorService.getAllSupervisors());
        return mav;
    }


    /**
     * Creates a new project.
     *
     * @param project The Project object representing the new project.
     * @return A RedirectView object redirecting to the admin projects page.
     */
    @PostMapping
    public RedirectView createProject(@ModelAttribute("project") Project project) {
        projectService.createProject(project);
        return new RedirectView("/admin/projects");
    }


    /**
     * Retrieves the page for updating a project.
     *
     * @param id The ID of the project to be updated.
     * @return A ModelAndView object for the update project page view.
     */
    @GetMapping("/edit/{id}")
    public ModelAndView getUpdateProjectPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/projectEditForm");
        Project project = projectService.getProjectById(id);
        mav.addObject("project", project);
        mav.addObject("allTypes", projectTypeService.getAllTypes());
        mav.addObject("allMethodologies",projectMethodologyService.getAllMethodologies());
        mav.addObject("allLanguages", projectLanguageService.getAllLanguages());
        mav.addObject("allLevels",skillLevelService.getAllLevels());
        mav.addObject("supervisors", supervisorService.getAllSupervisors());
        return mav;
    }

    /**
     * Updates a project.
     *
     * @param id      The ID of the project to be updated.
     * @param project The Project object containing updated project information.
     * @return A RedirectView object redirecting to the admin projects page.
     */
    @PostMapping("/edit/{id}")
    public RedirectView updateProject(@PathVariable("id") Integer id, @ModelAttribute Project project) {
        projectService.updateProject(id, project);
        return new RedirectView("/admin/projects");
    }


    /**
     * Retrieves the page displaying details of the selected project.
     *
     * @param id The ID of the project.
     * @return A ModelAndView object for the project detail page view.
     */
    @GetMapping("/show/{id}")
    public ModelAndView getProjectDetail(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/adminProjectDetail");
        Project project=projectService.getProjectById(id);
        mav.addObject("project", project);
        return mav;
    }

    /**
     * Deletes a project.
     *
     * @param id The ID of the project to be deleted.
     * @return A RedirectView object redirecting to the admin projects page.
     */
    @PostMapping("/{id}")
    public RedirectView deleteProject(@PathVariable("id") Integer id) {
        projectService.deleteProject(id);
        return new RedirectView("/admin/projects");
    }


}
