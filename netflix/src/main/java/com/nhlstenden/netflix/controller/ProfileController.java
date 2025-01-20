package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Get all profiles (supports JSON and XML)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        if (profiles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }
        return ResponseEntity.ok(profiles); // 200 OK
    }

    // Get profiles as CSV
    @GetMapping(value = "/csv", produces = "text/csv")
    public void getAllProfilesAsCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=profiles.csv");

        List<Profile> profiles = profileService.getAllProfiles();
        try (PrintWriter writer = response.getWriter()) {
            // Write CSV header
            writer.println("Profile ID,Profile Name,Profile Age,Preferred Language,Is Child Profile");

            // Write CSV rows
            for (Profile profile : profiles) {
                writer.println(String.format("%d,%s,%d,%s,%b",
                        profile.getProfileId(),
                        profile.getProfileName(),
                        profile.getProfileAge(),
                        profile.getPreferredLanguage(),
                        profile.getProfileChild()));
            }
        }
    }

    // Get profile by ID (supports JSON and XML)
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> getProfileById(@PathVariable Integer id) {
        try {
            Profile profile = profileService.getProfileById(id);
            return ResponseEntity.ok(profile); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage()); // 404 Not Found
        }
    }

    // Create a new profile
    @PostMapping
    public ResponseEntity<Object> createProfile(@RequestBody Profile profile) {
        try {
            Profile createdProfile = profileService.createProfile(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage()); // 400 Bad Request
        }
    }

    // Update an existing profile
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfile(@PathVariable Integer id, @RequestBody Profile profile) {
        try {
            Profile updatedProfile = profileService.updateProfile(id, profile);
            return ResponseEntity.ok(updatedProfile); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage()); // 404 Not Found
        }
    }

    // Delete a profile
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfile(@PathVariable Integer id) {
        try {
            profileService.deleteProfile(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage()); // 404 Not Found
        }
    }
}
