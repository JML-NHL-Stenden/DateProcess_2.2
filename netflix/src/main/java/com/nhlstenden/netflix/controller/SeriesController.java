package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Series;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/series")
public class SeriesController
{

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService)
    {
        this.seriesService = seriesService;
    }

    @GetMapping
    public ResponseEntity<List<Series>> getAllSeries()
    {
        return ResponseEntity.ok(seriesService.getAllSeries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSeriesById(@PathVariable Integer id) {
        try {
            Series series = seriesService.getSeriesById(id);
            return ResponseEntity.ok(series); // 200 OK
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", 404, "message", e.getMessage())); // 404 Not Found
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getSeriesByTitle(@RequestParam String title) {
        List<Series> series = seriesService.getSeriesByTitle(title);
        if (series.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }
        return ResponseEntity.ok(series); // 200 OK
    }


    @GetMapping("/featured")
    public ResponseEntity<List<Series>> getSeriesByFeatured(@RequestParam Boolean isFeatured)
    {
        return ResponseEntity.ok(seriesService.getSeriesByFeaturedStatus(isFeatured));
    }

    @PostMapping
    public ResponseEntity<Series> createSeries(@RequestBody Series series)
    {
        return ResponseEntity.ok(seriesService.createSeries(series));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Series> updateSeries(@PathVariable Integer id, @RequestBody Series series)
    {
        return ResponseEntity.ok(seriesService.updateSeries(id, series));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Integer id)
    {
        seriesService.deleteSeries(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/feature")
    public ResponseEntity<Series> markAsFeatured(@PathVariable Integer id)
    {
        return ResponseEntity.ok(seriesService.markAsFeatured(id));
    }

    @PatchMapping("/{id}/unfeature")
    public ResponseEntity<Series> markAsUnfeatured(@PathVariable Integer id)
    {
        return ResponseEntity.ok(seriesService.markAsUnfeatured(id));
    }
}
