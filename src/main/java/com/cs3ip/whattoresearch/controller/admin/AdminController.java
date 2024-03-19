package com.cs3ip.whattoresearch.controller.admin;


import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView getAdminDashboardPage(Principal principal) {
        ModelAndView mav = new ModelAndView("admin/adminProfile");
        String email = principal.getName();
        User currentUser = userRepository.findByEmail(email);
        mav.addObject("user", currentUser);
        return mav;
    }

}
