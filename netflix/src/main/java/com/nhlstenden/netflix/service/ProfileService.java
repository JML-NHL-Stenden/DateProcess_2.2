package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService
{
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository)
    {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles()
    {
        return profileRepository.findAll();
    }

    public Profile getProfile(Integer profileId)
    {
        Profile profile = profileRepository.findByProfileId(profileId);
        if (profileId == null)
        {
            throw new EntityNotFoundException("Profile not found with id: " + profileId);
        }
        return profile;
    }

    public Profile createProfile(Profile profile)
    {
        if (profileRepository.existsByProfileId(profile.getProfileId()))
        {
            throw new IllegalArgumentException("Profile already exists with id: " + profile.getProfileId());
        }
        return profileRepository.save(profile);
    }

    public Profile addOrUpdateProfile(Integer id, Profile profile)
    {
        Profile existingProfile = profileRepository.findById(id).orElse(null);
        if (existingProfile != null)
        {
            profile.setProfileId(existingProfile.getProfileId());
        }
        return profileRepository.save(profile);
    }

    public void deleteProfile(Integer id)
    {
        Profile profile = getProfile(id);
        profileRepository.deleteById(profile.getProfileId());
    }
}
