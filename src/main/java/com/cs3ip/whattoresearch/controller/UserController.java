package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.Contact;
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

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView mav = new ModelAndView("signup_form");
        mav.addObject("user", new User());
        return mav;
    }

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


    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }


    @GetMapping("/profile")
    public ModelAndView editCurrentUser(Principal principal) {
        ModelAndView mav = new ModelAndView("userProfile");
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        mav.addObject("user", currentUser);
        return mav;
    }


    @PostMapping("/edit-profile")
    public ModelAndView saveCurrentUser(@ModelAttribute("user") User user, Principal principal) {
        ModelAndView mav = new ModelAndView("userProfile");
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        currentUser.setEmail(user.getEmail());
//        currentUser.setPassword(user.getPassword());
        service.saveUser(currentUser);
        return mav;
    }



}