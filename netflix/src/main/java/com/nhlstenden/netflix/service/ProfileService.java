package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.repository.ProfileRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @PreAuthorize("hasRole('SENIOR') or hasRole('MEDIOR')") // Exclude Juniors
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
}
