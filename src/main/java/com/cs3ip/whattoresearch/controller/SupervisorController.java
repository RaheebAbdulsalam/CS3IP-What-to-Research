package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.Supervisor;
import com.cs3ip.whattoresearch.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * RESTful Controller for controlling supervisors .
 */
@RestController
@RequestMapping("/supervisors")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    /**
     * Retrieves the page displaying a list of supervisors.
     *
     * @return A ModelAndView object for the supervisors list page view.
     */

    @GetMapping
    public ModelAndView getSupervisorsPage() {
        ModelAndView mav = new ModelAndView("supervisorsList");
        List<Supervisor> supervisors = supervisorService.getAllSupervisors();
        mav.addObject("supervisors", supervisors);
        return mav;
    }
}
