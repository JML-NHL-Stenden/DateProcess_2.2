package com.nhlstenden.netflix.controller;

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

    @PutMapping("/{profileId}")
    public ResponseEntity<Profile> addOrUpdateProfile(@PathVariable Integer profileId, @RequestBody Profile profile)
    {
        Profile result = profileService.addOrUpdateProfile(profileId, profile);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Integer profileId)
    {
        profileService.deleteProfile(profileId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAllProfilesDirect()
    {
        Query query = entityManager.createNativeQuery("SELECT * FROM profile", Profile.class);
        @SuppressWarnings("unchecked")
        List<Profile> profiles = (List<Profile>) query.getResultList();
        return ResponseEntity.ok(profiles);
    }
}
