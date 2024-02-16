package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.Contact;
import com.cs3ip.whattoresearch.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * RESTful web service controller for managing contact messages.
 */
@RestController
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping()
    public ModelAndView viewHomePage(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("contact", new Contact());
        return mav;
    }


    /**
     * This method handles the submission of the contact form and saves the data to the database.
     * @param contact A Contact object containing the form data.
     * @return A ModelAndView object containing the contact view.
     */
    @PostMapping("/submit-form")
    public ModelAndView submitForm(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return new ModelAndView("contact");
    }

}
