package com.dk.jobby.jobpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dk.jobby.jobpost.domain.Profile;
import com.dk.jobby.jobpost.service.ProfileService;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public String viewProfilePage(Model model) {
        Long userId = getLoggedInUserId(); // Fetch the logged-in user's ID
        Profile profile = profileService.getLoggedInUserProfile(userId); // Pass userId explicitly
        model.addAttribute("profile", profile);
        return "profile";
    }

    private Long getLoggedInUserId() {
        // Fetch the logged-in user's ID from the authentication context
        Object principal = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof com.dk.jobby.jobpost.domain.User) { // Replace with your User class
            return ((com.dk.jobby.jobpost.domain.User) principal).getId(); // Replace with actual method to get userId
        }
        throw new IllegalStateException("Unable to fetch logged-in user ID");
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute Profile profile) {
        Long userId = profile.getId(); // Extract userId from the Profile object
        profileService.updateProfile(userId, profile); // Pass userId explicitly
        return "redirect:/profiles";
    }
}