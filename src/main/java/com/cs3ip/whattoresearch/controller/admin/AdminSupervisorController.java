package com.cs3ip.whattoresearch.controller.admin;


import com.cs3ip.whattoresearch.model.Supervisor;
import com.cs3ip.whattoresearch.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * RESTful Controller for controlling supervisors.
 */
@RestController
@RequestMapping("/admin/supervisors")
public class AdminSupervisorController {

    @Autowired
    private SupervisorService supervisorService;


    /**
     * Retrieves the admin supervisor page displaying a list of supervisors.
     *
     * @return A ModelAndView object for the admin supervisor page view.
     */
    @GetMapping
    public ModelAndView getSupervisorsPage() {
        ModelAndView mav = new ModelAndView("admin/supervisors");
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        mav.addObject("supervisors", supervisors);
        return mav;
    }


    /**
     * Retrieves the page for adding a new supervisor.
     *
     * @return A ModelAndView object for the add supervisor page view.
     */
    @GetMapping("/create")
    public ModelAndView getAddProjectPage() {
        ModelAndView mav = new ModelAndView("admin/addSupervisor");
        mav.addObject("supervisor", new Supervisor());
        return mav;
    }

    /**
     * Adds a new supervisor.
     *
     * @param supervisor The Supervisor object representing the new supervisor.
     * @return A RedirectView object redirecting to the admin supervisor page.
     */
    @PostMapping
    public RedirectView addSupervisor(@ModelAttribute("supervisor") Supervisor supervisor) {
        supervisorService.addSupervisor(supervisor);
        return new RedirectView("/admin/supervisors");
    }


    /**
     * Removes a supervisor.
     *
     * @param id The ID of the supervisor to be removed.
     * @return A RedirectView object redirecting to the admin supervisor page.
     */
    @PostMapping("/{id}")
    public RedirectView removeSupervisor(@PathVariable("id") Integer id) {
       supervisorService.removeSupervisor(id);
        return new RedirectView("/admin/supervisors");
    }
}
