package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Season;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService
{
    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository)
    {
        this.seasonRepository = seasonRepository;
    }

    public List<Season> getAllSeasons()
    {
        return seasonRepository.findAll();
    }

    public Season getSeasonById(Integer seasonId)
    {
        return seasonRepository.findById(seasonId)
                .orElseThrow(() -> new ResourceNotFoundException("Season with ID '" + seasonId + "' does not exist in the database"));
    }

    public List<Season> getSeasonsBySeriesId(Integer seriesId)
    {
        return seasonRepository.findBySeriesId(seriesId);
    }

    public Season getSeasonBySeriesIdAndNumber(Integer seriesId, Integer seasonNumber)
    {
        return seasonRepository.findBySeriesIdAndSeasonNumber(seriesId, seasonNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Season number '" + seasonNumber + "' for series ID '" + seriesId + "' does not exist in the database"));
    }

    public Season createSeason(Season season)
    {
        if (seasonRepository.findBySeriesIdAndSeasonNumber(season.getSeriesId(), season.getSeasonNumber()).isPresent())
        {
            throw new IllegalArgumentException("Season " + season.getSeasonNumber() + " already exists for series ID: " + season.getSeriesId());
        }
        return seasonRepository.save(season);
    }

    public Season updateSeason(Integer seasonId, Season season)
    {
        Season existingSeason = getSeasonById(seasonId);
        season.setSeasonId(existingSeason.getSeasonId());
        return seasonRepository.save(season);
    }

    public void deleteSeason(Integer seasonId)
    {
        Season season = getSeasonById(seasonId);
        seasonRepository.deleteById(season.getSeasonId());
    }
}