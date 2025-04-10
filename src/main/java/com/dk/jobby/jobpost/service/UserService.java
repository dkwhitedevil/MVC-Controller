package com.dk.jobby.jobpost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dk.jobby.jobpost.domain.User;
import com.dk.jobby.jobpost.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUserProfile(User user) {
        try {
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                existingUser.setEmail(user.getEmail());
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
                userRepository.save(existingUser);
                return true;
            }
        } catch (Exception e) {
            // Log the exception
        }
        return false;
    }

    public User getLoggedInUser() {
        // Replace with actual logic to fetch the logged-in user
        return userRepository.findByUsername("loggedInUsername"); 
    }

    public void updateUser(User user) {
        // Update the user in the database
        userRepository.save(user);
    }
}
