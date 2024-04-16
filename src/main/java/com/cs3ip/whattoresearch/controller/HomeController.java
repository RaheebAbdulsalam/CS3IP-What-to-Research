package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.Contact;
import com.cs3ip.whattoresearch.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * RESTful controller for Home Page.
 */
@RestController
public class HomeController {
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Displays the home page view.
     *
     * @return A ModelAndView object for the home page view.
     */
    @GetMapping()
    public ModelAndView viewHomePage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("contact", new Contact());
        return mav;
    }

    /**
     * Handles the submission of the contact form and saves the data to the database.
     *
     * @param contact A Contact object containing the form data.
     * @return A ModelAndView object for the success message view.
     */
    @PostMapping("/submit-form")
    public ModelAndView submitForm(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return new ModelAndView("successMessage");
    }

    /**
     * Displays the FAQ page view.
     *
     * @return A ModelAndView object for the FAQ page view.
     */
    @GetMapping("/faq")
    public ModelAndView faqPage() {
        ModelAndView mav = new ModelAndView("faq");
        return mav;
    }


}
