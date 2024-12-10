package com.nhlstenden.netflix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nhlstenden.netflix.service.ProfileService;



@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profile Management", description = "Profile operations")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

//    @PostMapping
//    @Operation(summary = "Create new profile")
//    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileCreationDTO dto) {
//        return ResponseEntity.ok(profileService.createProfile(dto));
//    }
//
//    @GetMapping("/{profileId}")
//    @Operation(summary = "Get profile details")
//    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long profileId) {
//        return ResponseEntity.ok(profileService.getProfile(profileId));
//    }

//    @PatchMapping("/{profileId}")
//    @Operation(summary = "Update profile")
//    public ResponseEntity<ProfileDTO> updateProfile(
//            @PathVariable Long profileId,
//            @RequestBody ProfileUpdateDTO dto) {
//        return ResponseEntity.ok(profileService.updateProfile(profileId, dto));
//    }

    @DeleteMapping("/{profileId}")
    @Operation(summary = "Delete profile")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
        profileService.deleteProfile(profileId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{profileId}/recommendations")
//    @Operation(summary = "Get profile recommendations")
//    public ResponseEntity<List<ContentDTO>> getRecommendations(@PathVariable Long profileId) {
//        return ResponseEntity.ok(profileService.getRecommendations(profileId));
//    }
}