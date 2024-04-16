package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.model.ShortList;
import com.cs3ip.whattoresearch.security.CustomUserDetails;
import com.cs3ip.whattoresearch.service.ShortListService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * RESTful Controller for controlling shortlisted projects.
 */
@RestController
@RequestMapping("/shortlist")
public class ShortListController {
    @Autowired
    private ShortListService shortListService;

    /**
     * Retrieves the shortlist page displaying the user's favorite projects.
     *
     * @param authentication The authentication object representing the user who is logged-in.
     * @return A ModelAndView object for the shortlist page view.
     */
    @GetMapping
    public ModelAndView getShortListPage(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<ShortList> favouriteProjects = shortListService.getFavouriteProjects(userDetails.getUser());

        ModelAndView modelAndView = new ModelAndView("shortList");
        modelAndView.addObject("favouriteProjects", favouriteProjects);
        return modelAndView;
    }

    /**
     * Adds a project to the user's shortlist.
     *
     * @param projectId      The ID of the project to be added.
     * @param authentication The authentication object representing the user who is logged-in.
     * @return A ResponseEntity indicating the success or failure of adding the project to the shortlist.
     */
    @PostMapping
    public ResponseEntity<String> addToShortList(@RequestParam("projectId") Integer projectId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        try {
            shortListService.addToShortList(userDetails.getUser(), projectId);
            return ResponseEntity.ok("Project added successfully to your shortlist.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add project to shortlist.");
        }
    }

    /**
     * Removes a project from the user's shortlist.
     *
     * @param projectId          The ID of the project to be removed from the shortlist.
     * @param authentication  The authentication object representing the user who is logged-in.
     * @param response        The HttpServletResponse object for redirecting to the shortlist page.
     * @return A ResponseEntity indicating the success of removing the project from the shortlist.
     */
    @PostMapping("/{projectId}")
    public ResponseEntity<?> removeFromShortList(@PathVariable Integer projectId, Authentication authentication, HttpServletResponse response) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        shortListService.removeShortListProject(userDetails.getUser(), projectId);
        response.setHeader("Location", "/shortlist");
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    /**
     * Retrieves the detail view of a specific project.
     *
     * @param id The ID of the project.
     * @return A ModelAndView object for the project detail page view.
     */
    @GetMapping("/display/{id}")
    public ModelAndView getProjectDetail(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/projectDetail");
        Project project= shortListService.getProjectById(id);
        mav.addObject("project", project);
        return mav;
    }


}
