package com.cs3ip.whattoresearch.controller;


import com.cs3ip.whattoresearch.model.WishList;
import com.cs3ip.whattoresearch.security.CustomUserDetails;
import com.cs3ip.whattoresearch.service.WishListService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;


    @GetMapping
    public ModelAndView getWishListPage(Authentication authentication) {
        // Users must log in to access their wishlist - return to login page
        if (authentication == null) {
            return new ModelAndView("redirect:/login");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<WishList> favouriteProjects = wishListService.getFavouriteProjects(userDetails.getUser());

        ModelAndView modelAndView = new ModelAndView("wishlist");
        modelAndView.addObject("favouriteProjects", favouriteProjects);
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<String> addToWishList(@RequestParam("projectId") Integer projectId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        wishListService.addToWishList(userDetails.getUser(), projectId);
        return ResponseEntity.ok("Project added successfully to your wishlist.");
    }


    @PostMapping("/{itemId}")
    public ResponseEntity<?> removeFromWishList(@PathVariable Integer itemId, Authentication authentication, HttpServletResponse response) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        wishListService.removeWishListProject(userDetails.getUser(), itemId);
        response.setHeader("Location", "/wishlist");
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }


}
