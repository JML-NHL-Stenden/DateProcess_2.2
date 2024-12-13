package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Series;
import com.nhlstenden.netflix.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Series> getSeriesById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(seriesService.getSeriesById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Series>> getSeriesByTitle(@RequestParam String title)
    {
        return ResponseEntity.ok(seriesService.getSeriesByTitle(title));
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
