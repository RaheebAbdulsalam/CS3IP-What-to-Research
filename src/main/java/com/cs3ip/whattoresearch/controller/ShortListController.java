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

@RestController
@RequestMapping("/shortlist")
public class ShortListController {

    @Autowired
    private ShortListService shortListService;

    @GetMapping
    public ModelAndView getShortListPage(Authentication authentication) {
        // Users must log in to access their shortlist - return to login page
        if (authentication == null) {
            return new ModelAndView("redirect:/login");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<ShortList> favouriteProjects = shortListService.getFavouriteProjects(userDetails.getUser());

        ModelAndView modelAndView = new ModelAndView("shortList");
        modelAndView.addObject("favouriteProjects", favouriteProjects);
        return modelAndView;
    }

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


    @PostMapping("/{itemId}")
    public ResponseEntity<?> removeFromShortList(@PathVariable Integer itemId, Authentication authentication, HttpServletResponse response) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        shortListService.removeShortListProject(userDetails.getUser(), itemId);
        response.setHeader("Location", "/shortlist");
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @GetMapping("/display/{id}")
    public ModelAndView getProjectDetail(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("/projectDetail");
        Project project= shortListService.getProjectById(id);
        mav.addObject("project", project);
        return mav;
    }


}
