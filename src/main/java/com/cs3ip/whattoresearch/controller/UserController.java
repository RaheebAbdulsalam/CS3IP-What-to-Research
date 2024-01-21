package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.repository.UserRepository;
import com.cs3ip.whattoresearch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Rest controller for handling home page and registration related requests.
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepo;

    /**
     * Returns home page.
     * @param model Model object
     * @param principal Principal object
     * @return ModelAndView object of the home page
     */
    @GetMapping()
    public ModelAndView viewHomePage(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    /**
     * Returns registration page.
     * @return ModelAndView object of the registration page
     */
    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView mav = new ModelAndView("signup_form");
        mav.addObject("user", new User());
        return mav;
    }

    /**
     * Returns page for registering the user.
     * @param user User object
     * @param result BindingResult object
     * @return ModelAndView of the success page or the registration page with error message
     */
    @PostMapping("/process_register")
    public ModelAndView processRegister(@Valid User user, BindingResult result) {
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView("signup_form");
            return mav;
        }

        if (service.emailExists(user.getEmail())) {
            mav = new ModelAndView("signup_form");
            mav.addObject("errorMessage", "Email already exists!");
            return mav;
        }

        service.saveWithDefaultRole(user);
        mav = new ModelAndView("register_success");
        return mav;
    }


    /**
     * Returns the login page.
     *
     * @return ModelAndView representing the login page
     */
    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }


}