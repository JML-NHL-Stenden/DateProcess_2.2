package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController
{

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService)
    {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles()
    {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }
}
