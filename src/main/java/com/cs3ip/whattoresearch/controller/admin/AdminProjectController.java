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

    // Returns admin project page
    @GetMapping
    public ModelAndView getAdminProjectPage(@RequestParam(required = false) String query) {
        ModelAndView mav = new ModelAndView("admin/projects");
        List<Project> projects = projectService.getAllProjects();
        mav.addObject("projects", projects);
        return mav;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    // returns page to add project
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


    // creates a project and returns to project page
    @PostMapping
    public RedirectView createProject(@ModelAttribute("project") Project project) {
        projectService.createProject(project);
        return new RedirectView("/admin/projects");
    }



    // returns a ModelAndView of the update product page
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
//        mav.addObject("supervisors",supervisorService.getAllSupervisors());
        return mav;
    }

    // method for updating a product
    @PostMapping("/edit/{id}")
    public RedirectView updateProject(@PathVariable("id") Integer id, @ModelAttribute Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        return new RedirectView("/admin/projects");
    }


    // Returns project details page
    @GetMapping("/show/{id}")
    public ModelAndView getProjectDetail(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/admin/adminProjectDetail");
        Project project=projectService.getProjectById(id);
        mav.addObject("project", project);
        return mav;
    }


    // method for deleting projects
    @PostMapping("/{id}")
    public RedirectView deleteProject(@PathVariable("id") Integer id) {
        projectService.deleteProject(id);
        return new RedirectView("/admin/projects");
    }




}
