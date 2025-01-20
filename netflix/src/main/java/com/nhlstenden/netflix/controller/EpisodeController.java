package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Episode;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/episodes")
public class EpisodeController
{

    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService)
    {
        this.episodeService = episodeService;
    }

    @GetMapping
    public ResponseEntity<List<Episode>> getAllEpisodes()
    {
        List<Episode> episodes = episodeService.getAllEpisodes();
        if (episodes.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(episodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEpisodeById(@PathVariable Integer id)
    {
        try
        {
            Episode episode = episodeService.getEpisodeById(id);
            return ResponseEntity.ok(episode);
        } catch (ResourceNotFoundException e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 404);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createEpisode(@RequestBody Episode episode)
    {
        try
        {
            Episode createdEpisode = episodeService.createEpisode(episode);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEpisode);
        } catch (IllegalArgumentException e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 400);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEpisode(@PathVariable Integer id, @RequestBody Episode episode)
    {
        try
        {
            Episode updatedEpisode = episodeService.updateEpisode(id, episode);
            return ResponseEntity.ok(updatedEpisode);
        } catch (ResourceNotFoundException e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 404);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEpisode(@PathVariable Integer id)
    {
        try
        {
            episodeService.deleteEpisode(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 404);
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", 500);
            errorResponse.put("message", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
