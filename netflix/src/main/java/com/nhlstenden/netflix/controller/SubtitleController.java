package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.entity.Subtitle;
import com.nhlstenden.netflix.service.SubtitleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subtitles")
public class SubtitleController
{
    private  final SubtitleService subtitleService;
    private final EntityManager entityManager;

    @Autowired
    public SubtitleController(SubtitleService subtitleService, EntityManager entityManager)
    {
        this.subtitleService = subtitleService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<Subtitle>> getAllSubtitle()
    {
        return ResponseEntity.ok(subtitleService.getAllSubtitle());
    }

    @GetMapping("/{subtitleLanguage}")
    public ResponseEntity<Subtitle> getSubtitle(@PathVariable String subtitleLanguage)
    {
        return ResponseEntity.ok(subtitleService.getSubtitle(subtitleLanguage));
    }

    @PostMapping
    public ResponseEntity<Subtitle> createSubtitle(@RequestBody Subtitle subtitle)
    {
        return ResponseEntity.ok(subtitleService.createSubtitle(subtitle));
    }

    @PutMapping("/{subtitleLanguage}")
    public ResponseEntity<Subtitle> addOrUpdateSubtitle(@PathVariable String subtitleLanguage, @RequestBody Subtitle subtitle)
    {
        Subtitle result = subtitleService.addOrUpdateSubtitle(subtitleLanguage, subtitle);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{subtitleLanguage}")
    public ResponseEntity<Void> deleteSubtitle(@PathVariable String subtitleLanguage)
    {
        subtitleService.deleteSubtitle(subtitleLanguage);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subtitle>> getAllSubtitleDirect()
    {
        Query query = entityManager.createNativeQuery("SELECT * FROM subtitle", Subtitle.class);
        @SuppressWarnings("unchecked")
        List<Subtitle> subtitles = (List<Subtitle>) query.getResultList();
        return ResponseEntity.ok(subtitles);
    }
}
