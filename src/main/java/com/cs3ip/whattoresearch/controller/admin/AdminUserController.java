package com.cs3ip.whattoresearch.controller.admin;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.model.Role;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getAdminUsersPage() {
        ModelAndView mav = new ModelAndView("admin/user");
        List<User> listUsers = userService.listAll();
        mav.addObject("users", listUsers);
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getUpdateUserPage(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/userEditForm");
        User user= userService.getUserById(id);
        List<Role> listRoles = userService.getRoles();
        mav.addObject("user", user);
        mav.addObject("allRoles", listRoles);
        return mav;
    }


    @PostMapping("/edit/{id}")
    public RedirectView updateUser(@PathVariable("id") Integer id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return new RedirectView("/admin/user");
    }


    // method for deleting projects, and reloading page
    @PostMapping("/{id}")
    public RedirectView removeUser(@PathVariable("id") Integer id) {
        userService.removeUser(id);
        return new RedirectView("/admin/user");
    }






}
