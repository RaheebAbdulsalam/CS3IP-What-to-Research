package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.service.ProjectService;
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

        // Returns admin product page and product list
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
            if(projects.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }


// method for deleting projects, and reloading page
@PostMapping("/{id}")
    public RedirectView deleteProject(@PathVariable("id") Integer id) {
        projectService.deleteProject(id);
        return new RedirectView("/admin/projects");
    }


}
