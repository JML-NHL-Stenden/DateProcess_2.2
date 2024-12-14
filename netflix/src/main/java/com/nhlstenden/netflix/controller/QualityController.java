package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Quality;
import com.nhlstenden.netflix.service.QualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualities")
public class QualityController
{
    private final QualityService qualityService;

    @Autowired
    public QualityController(QualityService qualityService)
    {
        this.qualityService = qualityService;
    }

    @GetMapping
    public ResponseEntity<List<Quality>> getAllQualities()
    {
        return ResponseEntity.ok(qualityService.getAllQualities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quality> getQualityById(@PathVariable("id") Integer qualityId)
    {
        return ResponseEntity.ok(qualityService.getQualityById(qualityId));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Quality> getQualityByName(@PathVariable("name") String quality)
    {
        return ResponseEntity.ok(qualityService.getQualityByName(quality));
    }

    @PostMapping
    public ResponseEntity<Quality> createQuality(@RequestBody Quality quality)
    {
        return ResponseEntity.ok(qualityService.createQuality(quality));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quality> updateQuality(@PathVariable("id") Integer qualityId, @RequestBody Quality quality)
    {
        return ResponseEntity.ok(qualityService.updateQuality(qualityId, quality));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuality(@PathVariable("id") Integer qualityId)
    {
        qualityService.deleteQuality(qualityId);
        return ResponseEntity.noContent().build();
    }
}