package com.nhlstenden.netflix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/watchlist")
@Tag(name = "Watchlist Management", description = "Watchlist operations")
public class WatchlistController {
//    @Autowired
//    private WatchlistService watchlistService;
//
//    @PostMapping("/profile/{profileId}")
//    @Operation(summary = "Add to watchlist")
//    public ResponseEntity<WatchlistDTO> addToWatchlist(
//            @PathVariable Long profileId,
//            @RequestBody WatchlistItemDTO dto) {
//        return ResponseEntity.ok(watchlistService.addToWatchlist(profileId, dto));
//    }

//    @GetMapping("/profile/{profileId}")
//    @Operation(summary = "Get profile watchlist")
//    public ResponseEntity<List<WatchlistDTO>> getWatchlist(@PathVariable Long profileId) {
//        return ResponseEntity.ok(watchlistService.getWatchlist(profileId));
//    }

//    @DeleteMapping("/{watchlistId}")
//    @Operation(summary = "Remove from watchlist")
//    public ResponseEntity<Void> removeFromWatchlist(@PathVariable Long watchlistId) {
//        watchlistService.removeFromWatchlist(watchlistId);
//        return ResponseEntity.ok().build();
//    }
}