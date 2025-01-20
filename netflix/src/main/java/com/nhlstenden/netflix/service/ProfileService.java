package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // Fetch all profiles
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    // Fetch profile by ID
    public Profile getProfileById(Integer id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile with ID '" + id + "' does not exist."));
    }

    // Create a new profile
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    // Update an existing profile
    public Profile updateProfile(Integer id, Profile profile) {
        Profile existingProfile = getProfileById(id);
        existingProfile.setProfileName(profile.getProfileName());
        existingProfile.setProfileImage(profile.getProfileImage());
        existingProfile.setProfileAge(profile.getProfileAge());
        existingProfile.setPreferredLanguage(profile.getPreferredLanguage());
        existingProfile.setProfileChild(profile.getProfileChild());
        return profileRepository.save(existingProfile);
    }

    // Delete a profile
    public void deleteProfile(Integer id) {
        Profile existingProfile = getProfileById(id);
        profileRepository.delete(existingProfile);
    }
}
