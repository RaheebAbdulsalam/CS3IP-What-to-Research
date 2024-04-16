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


/**
 * RESTful controller for controlling user-related functionalities.
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepo;

    /**
     * Displays the user registration form.
     *
     * @return A ModelAndView object for the registration form view.
     */
    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView mav = new ModelAndView("signup_form");
        mav.addObject("user", new User());
        return mav;
    }

    /**
     * Processes user registration.
     *
     * @param user   The User object containing user registration data.
     * @param result The BindingResult object for validation results.
     * @return A ModelAndView object indicating the success or failure of the registration process.
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
     * Displays the login page.
     *
     * @return A ModelAndView object for the login page view.
     */
    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    /**
     * Displays the user profile page.
     *
     * @param principal The Principal object representing the user who is logged-in .
     * @return A ModelAndView object for the user profile page view.
     */

    @GetMapping("/profile")
    public ModelAndView userProfilePage(Principal principal) {
        ModelAndView mav = new ModelAndView("userProfile");
        String email = principal.getName();
        User currentUser = userRepo.findByEmail(email);
        mav.addObject("user", currentUser);
        return mav;
    }

    /**
     * View the change password page.
     *
     * @param principal The Principal object representing the user who is logged-in .
     * @return A ModelAndView object for the change password page view.
     */
    @GetMapping("profile/change-password")
    public ModelAndView showChangePasswordPage(Principal principal) {
        ModelAndView mav = new ModelAndView("change_password");
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        mav.addObject("user", user);
        return mav;
    }

    /**
     * Processes the change password request.
     *
     * @param user      The User object containing the new password.
     * @param result    The BindingResult object for validation results.
     * @param principal The Principal object representing the user who is logged-in.
     * @return A ModelAndView object indicating the success or failure of the password change process.
     */
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




