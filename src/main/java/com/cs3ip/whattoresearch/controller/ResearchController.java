package com.cs3ip.whattoresearch.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/research")
public class ResearchController {

    // Returns research form page
    @GetMapping
    public ModelAndView getResearchForm() {
        ModelAndView mav = new ModelAndView("/research-form");
        return mav;
    }

}
