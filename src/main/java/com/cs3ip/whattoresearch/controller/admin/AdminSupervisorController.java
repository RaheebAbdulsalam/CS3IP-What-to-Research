package com.cs3ip.whattoresearch.controller.admin;


import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.model.Supervisor;
import com.cs3ip.whattoresearch.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/admin/supervisors")
public class AdminSupervisorController {

    @Autowired
    private SupervisorService supervisorService;


    // Returns admin project page
    @GetMapping
    public ModelAndView getSupervisorsPage(@RequestParam(required = false) String query) {
        ModelAndView mav = new ModelAndView("admin/supervisors");
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        mav.addObject("supervisors", supervisors);
        return mav;
    }


    @GetMapping("/list")
    public ResponseEntity<List<Supervisor>> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        if (supervisors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(supervisors, HttpStatus.OK);
    }

    // returns page to add project
    @GetMapping("/create")
    public ModelAndView getAddProjectPage() {
        ModelAndView mav = new ModelAndView("admin/addSupervisor");
        mav.addObject("supervisor", new Supervisor());
        return mav;
    }

    // creates a project and returns to project page
    @PostMapping
    public RedirectView addSupervisor(@ModelAttribute("supervisor") Supervisor supervisor) {
        supervisorService.addSupervisor(supervisor);
        return new RedirectView("/admin/supervisors");
    }


    // method for deleting projects, and reloading page
    @PostMapping("/{id}")
    public RedirectView removeSupervisor(@PathVariable("id") Integer id) {
       supervisorService.removeSupervisor(id);
        return new RedirectView("/admin/supervisors");
    }
}
