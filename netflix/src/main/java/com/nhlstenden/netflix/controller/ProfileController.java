package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.service.ProfileService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
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
    public ResponseEntity<List<Profile>> getAllProfiles()
    {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfile(@PathVariable Integer profileId)
    {
        return ResponseEntity.ok(profileService.getProfile(profileId));
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile)
    {
        return ResponseEntity.ok(profileService.createProfile(profile));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllProfilesDirect()
    {
        Query query = entityManager.createNativeQuery("SELECT * FROM profile", Profile.class);
        @SuppressWarnings("unchecked")
        List<Account> profiles = (List<Account>) query.getResultList();
        return ResponseEntity.ok(profiles);
    }
}
