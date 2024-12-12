package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.service.ProfileService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController
{

    private final ProfileService profileService;
    private final EntityManager entityManager;

    @Autowired
    public ProfileController(ProfileService profileService, EntityManager entityManager)
    {
        this.profileService = profileService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiless()
    {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile(@PathVariable Integer profileId)
    {
        return ResponseEntity.ok(profileService.getProfileById(profileId));
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile)
    {
        return ResponseEntity.ok(profileService.createProfile(profile));
    }
}
