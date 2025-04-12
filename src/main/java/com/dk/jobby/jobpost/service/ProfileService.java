package com.dk.jobby.jobpost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dk.jobby.jobpost.domain.Profile;
import com.dk.jobby.jobpost.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile getLoggedInUserProfile(Long userId) {
        return profileRepository.findById(userId).orElseGet(() -> {
            Profile defaultProfile = new Profile();
            defaultProfile.setId(userId); // Associate the profile with the userId
            defaultProfile.setFullName("");
            defaultProfile.setPhoneNumber("");
            defaultProfile.setDateOfBirth("");
            defaultProfile.setGender("");
            defaultProfile.setCountry("");
            defaultProfile.setState("");
            defaultProfile.setCity("");
            defaultProfile.setPreferredLanguage("");
            defaultProfile.setHobbies("");
            defaultProfile.setSkills("");
            defaultProfile.setCurrentJobTitle("");
            defaultProfile.setYearsOfExperience(0);
            return defaultProfile;
        });
    }

    public void updateProfile(Long userId, Profile profile) {
        profile.setId(userId); // Ensure the profile is associated with the correct userId
        profileRepository.save(profile);
    }
}