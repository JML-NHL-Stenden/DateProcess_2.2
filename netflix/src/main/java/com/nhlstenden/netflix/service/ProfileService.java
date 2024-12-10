package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhlstenden.netflix.repository.ProfileRepository;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AccountService accountService;

    public Profile createProfile(Profile profile, Long accountId) {
        profile.setAccount(accountService.getAccountById(accountId));
        return profileRepository.save(profile);
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public List<Profile> getProfilesByAccountId(Long accountId) {
        return profileRepository.findByAccountId(accountId);
    }

    public Profile updateProfile(Long id, Profile profileDetails) {
        Profile profile = getProfileById(id);
        profile.setProfileName(profileDetails.getProfileName());
        profile.setProfileImage(profileDetails.getProfileImage());
        profile.setProfileAge(profileDetails.getProfileAge());
        profile.setIsProfileChild(profileDetails.getIsProfileChild());
        profile.setLanguage(profileDetails.getLanguage());
        return profileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        Profile profile = getProfileById(id);
        profileRepository.delete(profile);
    }

    public List<Profile> getChildProfiles(Long accountId) {
        return profileRepository.findByAccountIdAndIsProfileChild(accountId, true);
    }

    public boolean isProfileNameTaken(Long accountId, String profileName) {
        return profileRepository.existsByAccountIdAndProfileName(accountId, profileName);
    }
}