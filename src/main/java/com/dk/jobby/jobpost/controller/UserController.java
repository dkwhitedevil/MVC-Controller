package com.dk.jobby.jobpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dk.jobby.jobpost.domain.User;
import com.dk.jobby.jobpost.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/profile") // Changed endpoint to avoid conflict
    public String viewProfile(Model model) {
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
            return "profile-page"; // Replace with the actual profile page template name
        }
        return "redirect:/login"; // Redirect to login if no user is logged in
    }
}
