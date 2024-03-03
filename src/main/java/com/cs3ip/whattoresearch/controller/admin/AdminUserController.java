package com.cs3ip.whattoresearch.controller.admin;

import com.cs3ip.whattoresearch.model.Role;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ModelAndView getAdminUsersPage() {
        ModelAndView mav = new ModelAndView("admin/user");
        List<User> listUsers = service.listAll();
        mav.addObject("users", listUsers);
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("admin/userEditForm");
        User user = service.getUserId(id);
        List<Role> listRoles = service.getRoles();
        mav.addObject("user", user);
        mav.addObject("listRoles", listRoles);
        return mav;
    }

    @PostMapping("/save")
    public ModelAndView saveUser(User user) {
        ModelAndView mav = new ModelAndView("redirect:/admin/user");
        service.saveUser(user);
        return mav;
    }

}
