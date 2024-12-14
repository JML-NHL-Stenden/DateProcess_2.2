package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Episode;
import com.nhlstenden.netflix.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping
    public ResponseEntity<List<Episode>> getAllEpisodes() {
        return ResponseEntity.ok(episodeService.getAllEpisodes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Episode> getEpisodeById(@PathVariable Integer id) {
        return ResponseEntity.ok(episodeService.getEpisodeById(id));
    }

    @PostMapping
    public ResponseEntity<Episode> createEpisode(@RequestBody Episode episode) {
        return ResponseEntity.ok(episodeService.createEpisode(episode));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable Integer id, @RequestBody Episode episode) {
        return ResponseEntity.ok(episodeService.updateEpisode(id, episode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.noContent().build();
    }
}
