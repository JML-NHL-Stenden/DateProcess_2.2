package com.nhlstenden.netflix.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nhlstenden.netflix.service.SeriesService;

@RestController
@RequestMapping("/api/v1/series")
@Tag(name = "Series Management", description = "Series operations")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

//    @PostMapping
//    @Operation(summary = "Add new series")
//    public ResponseEntity<SeriesDTO> addSeries(@RequestBody SeriesCreationDTO dto) {
//        return ResponseEntity.ok(seriesService.addSeries(dto));
//    }

//    @GetMapping
//    @Operation(summary = "Get all series")
//    public ResponseEntity<Page<SeriesDTO>> getAllSeries(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return ResponseEntity.ok(seriesService.getAllSeries(page, size));
//    }

//    @GetMapping("/{seriesId}")
//    @Operation(summary = "Get series details")
//    public ResponseEntity<SeriesDTO> getSeries(@PathVariable Long seriesId) {
//        return ResponseEntity.ok(seriesService.getSeries(seriesId));
//    }
//
//    @PostMapping("/{seriesId}/seasons")
//    @Operation(summary = "Add new season")
//    public ResponseEntity<SeasonDTO> addSeason(
//            @PathVariable Long seriesId,
//            @RequestBody SeasonCreationDTO dto) {
//        return ResponseEntity.ok(seriesService.addSeason(seriesId, dto));
//    }

//    @GetMapping("/{seriesId}/seasons/{seasonId}/episodes")
//    @Operation(summary = "Get season episodes")
//    public ResponseEntity<List<EpisodeDTO>> getSeasonEpisodes(
//            @PathVariable Long seriesId,
//            @PathVariable Long seasonId) {
//        return ResponseEntity.ok(seriesService.getSeasonEpisodes(seriesId, seasonId));
//    }
}
