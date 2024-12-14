package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Season;
import com.nhlstenden.netflix.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seasons")
public class SeasonController
{
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService)
    {
        this.seasonService = seasonService;
    }

    @GetMapping
    public ResponseEntity<List<Season>> getAllSeasons()
    {
        return ResponseEntity.ok(seasonService.getAllSeasons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Season> getSeasonById(@PathVariable("id") Integer seasonId)
    {
        return ResponseEntity.ok(seasonService.getSeasonById(seasonId));
    }

    @GetMapping("/series/{seriesId}")
    public ResponseEntity<List<Season>> getSeasonsBySeriesId(@PathVariable Integer seriesId)
    {
        return ResponseEntity.ok(seasonService.getSeasonsBySeriesId(seriesId));
    }

    @GetMapping("/series/{seriesId}/number/{number}")
    public ResponseEntity<Season> getSeasonBySeriesIdAndNumber(
            @PathVariable Integer seriesId,
            @PathVariable Integer number)
    {
        return ResponseEntity.ok(seasonService.getSeasonBySeriesIdAndNumber(seriesId, number));
    }

    @PostMapping
    public ResponseEntity<Season> createSeason(@RequestBody Season season)
    {
        return ResponseEntity.ok(seasonService.createSeason(season));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Season> updateSeason(@PathVariable("id") Integer seasonId, @RequestBody Season season)
    {
        return ResponseEntity.ok(seasonService.updateSeason(seasonId, season));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable("id") Integer seasonId)
    {
        seasonService.deleteSeason(seasonId);
        return ResponseEntity.noContent().build();
    }
}