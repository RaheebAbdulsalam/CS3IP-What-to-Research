package com.cs3ip.whattoresearch.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/admin")
public class AdminController {

    // Returns admin profile page
    @GetMapping
    public ModelAndView getAdminHomePage() {
        ModelAndView mav = new ModelAndView("admin/adminProfile");
          return mav;
    }

}
