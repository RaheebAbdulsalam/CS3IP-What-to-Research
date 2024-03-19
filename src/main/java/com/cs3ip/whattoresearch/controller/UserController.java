package com.cs3ip.whattoresearch.controller;

import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.repository.UserRepository;
import com.cs3ip.whattoresearch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView userProfilePage(Principal principal) {
        ModelAndView mav = new ModelAndView("userProfile");
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        mav.addObject("user", currentUser);
        return mav;
    }


    @GetMapping("profile/edit-profile")
    public ModelAndView showEditProfilePage(Principal principal) {
        ModelAndView mav = new ModelAndView("edit_profile");
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        mav.addObject("user", user);
        return mav;
    }


    @PostMapping("/edit-profile")
    public ModelAndView processEditProfile(@Valid User user, BindingResult result, Principal principal) {
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView("edit_profile");
            return mav;
        }
        String loggedInEmail = principal.getName();
        User existingUser = userRepo.findByEmail(loggedInEmail);
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        service.updateProfile(existingUser);
        mav = new ModelAndView("edit_profile");
        mav.addObject("message", "You have successfully updated your Profile!");
        return mav;
    }

    @GetMapping("profile/change-password")
    public ModelAndView showChangePasswordPage(Principal principal) {
        ModelAndView mav = new ModelAndView("change_password");
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/change-password")
    public ModelAndView changePassword(@Valid User user, BindingResult result, Principal principal) {
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView("change_password");
            return mav;
        }
        String loggedInEmail = principal.getName();
        User existingUser = userRepo.findByEmail(loggedInEmail);
        existingUser.setPassword(user.getPassword());
        service.saveUser(existingUser);
        mav = new ModelAndView("change_password");
        mav.addObject("message", "You have successfully updated your Password!");
        return mav;
    }


}




