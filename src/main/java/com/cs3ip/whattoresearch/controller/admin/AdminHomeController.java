package com.cs3ip.whattoresearch.controller.admin;


import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * RESTful Controller for controlling admin dashboard.
 */
@RestController
@RequestMapping("/admin")
public class AdminHomeController {

    @Autowired
    private UserRepository userRepository;


    /**
     * Retrieves the admin dashboard page.
     *
     * @param principal The Principal object representing the logged-in admin.
     * @return A ModelAndView object for the admin dashboard page view.
     */
    @GetMapping
    public ModelAndView getAdminDashboardPage(Principal principal) {
        ModelAndView mav = new ModelAndView("admin/adminProfile");
        String email = principal.getName();
        User currentUser = userRepository.findByEmail(email);
        mav.addObject("user", currentUser);
        return mav;
    }

}
