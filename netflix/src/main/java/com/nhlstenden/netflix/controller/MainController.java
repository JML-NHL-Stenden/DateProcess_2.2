package com.nhlstenden.netflix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nhlstenden.netflix.service.AccountService;
import com.nhlstenden.netflix.service.MovieService;
import com.nhlstenden.netflix.service.ProfileService;
import com.nhlstenden.netflix.service.SeriesService;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Main Controller", description = "Main operations for Netflix application")
@SecurityRequirement(name = "bearerAuth")
public class MainController {

    @Autowired private MovieService movieService;
    @Autowired private SeriesService seriesService;
    @Autowired private ProfileService profileService;
    @Autowired private AccountService accountService;

    @GetMapping("/health")
    @Operation(summary = "Check application health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Application is running");
    }

//    @GetMapping("/stats")
//    @Operation(summary = "Get platform statistics")
//    public ResponseEntity<Map<String, Object>> getPlatformStats() {
//        Map<String, Object> stats = new HashMap<>();
//        stats.put("totalMovies", movieService.getTotalMovies());
//        stats.put("totalSeries", seriesService.getTotalSeries());
//        stats.put("totalProfiles", profileService.getTotalProfiles());
//        stats.put("totalAccounts", accountService.getTotalAccounts());
//        return ResponseEntity.ok(stats);
//    }

//    @GetMapping("/trending")
//    @Operation(summary = "Get trending content")
//    public ResponseEntity<Map<String, Object>> getTrending() {
//        Map<String, Object> trending = new HashMap<>();
//        trending.put("trendingMovies", movieService.getTrendingMovies(10));
//        trending.put("trendingSeries", seriesService.getTrendingSeries(10));
//        return ResponseEntity.ok(trending);
//    }
}