package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Season;
import com.nhlstenden.netflix.entity.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nhlstenden.netflix.repository.SeasonRepository;
import com.nhlstenden.netflix.repository.SeriesRepository;

import java.util.List;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }

    public Series getSeriesById(Long id) {
        return seriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Series not found"));
    }

    public Page<Series> getAllSeries(Pageable pageable) {
        return seriesRepository.findAll(pageable);
    }

    public Series updateSeries(Long id, Series seriesDetails) {
        Series series = getSeriesById(id);
        series.setTitle(seriesDetails.getTitle());
        series.setDescription(seriesDetails.getDescription());
        series.setTotalSeasons(seriesDetails.getTotalSeasons());
        series.setGenres(seriesDetails.getGenres());
        return seriesRepository.save(series);
    }

    @Transactional
    public Season addSeasonToSeries(Long seriesId, Season season) {
        Series series = getSeriesById(seriesId);
        season.setSeries(series);
        return seasonRepository.save(season);
    }

    public List<Season> getSeriesSeasons(Long seriesId) {
        return seasonRepository.findBySeriesId(seriesId);
    }

    public List<Series> getSeriesByGenre(Long genreId) {
        return seriesRepository.findByGenreId(genreId);
    }

    public Page<Series> searchSeries(String title, Pageable pageable) {
        return seriesRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }

    public List<Series> searchByKeyword(String keyword) {
        return seriesRepository.searchByKeyword(keyword);
    }
}