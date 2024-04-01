package com.cs3ip.whattoresearch.controller.admin;

import com.cs3ip.whattoresearch.model.Role;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;


/**
 * RESTful Controller for controlling users.
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves the admin users page for displaying a list of users.
     *
     * @return A ModelAndView object for the admin users page view.
     */
    @GetMapping
    public ModelAndView getAdminUsersPage() {
        ModelAndView mav = new ModelAndView("admin/user");
        List<User> listUsers = userService.listAll();
        mav.addObject("users", listUsers);
        return mav;
    }

    /**
     * Retrieves the page for updating a user information.
     *
     * @param id The ID of the user to be updated.
     * @return A ModelAndView object for the user update form view.
     */
    @GetMapping("/edit/{id}")
    public ModelAndView getUpdateUserPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/userEditForm");
        User user= userService.getUserById(id);
        List<Role> listRoles = userService.getRoles();
        mav.addObject("user", user);
        mav.addObject("allRoles", listRoles);
        return mav;
    }

    /**
     * Updates user information.
     *
     * @param id   The ID of the user to be updated.
     * @param user The User object containing updated user information.
     * @return A RedirectView object redirecting to the admin users page.
     */
    @PostMapping("/edit/{id}")
    public RedirectView updateUser(@PathVariable("id") Integer id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return new RedirectView("/admin/user");
    }


    /**
     * Removes a user.
     *
     * @param id The ID of the user to be removed.
     * @return A RedirectView object redirecting to the admin users page.
     */
    @PostMapping("/{id}")
    public RedirectView removeUser(@PathVariable("id") Integer id) {
        userService.removeUser(id);
        return new RedirectView("/admin/user");
    }






}
