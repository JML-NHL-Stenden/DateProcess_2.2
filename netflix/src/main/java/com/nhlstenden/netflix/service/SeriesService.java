package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Series;
import com.nhlstenden.netflix.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    public Series getSeriesById(Integer seriesId) {
        return seriesRepository.findById(seriesId)
                .orElseThrow(() -> new EntityNotFoundException("Series with ID " + seriesId + " not found."));
    }

    public List<Series> getSeriesByTitle(String title) {
        return seriesRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Series> getSeriesByFeaturedStatus(Boolean isFeatured) {
        return seriesRepository.findByIsFeatured(isFeatured);
    }

    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }

    public Series updateSeries(Integer seriesId, Series updatedSeries) {
        Series existingSeries = getSeriesById(seriesId);
        existingSeries.setTitle(updatedSeries.getTitle());
        existingSeries.setDescription(updatedSeries.getDescription());
        existingSeries.setTotalSeasons(updatedSeries.getTotalSeasons());
        existingSeries.setIsFeatured(updatedSeries.getIsFeatured());
        return seriesRepository.save(existingSeries);
    }

    public void deleteSeries(Integer seriesId) {
        Series series = getSeriesById(seriesId);
        seriesRepository.delete(series);
    }

    public Series markAsFeatured(Integer seriesId) {
        Series series = getSeriesById(seriesId);
        series.setIsFeatured(true);
        return seriesRepository.save(series);
    }

    public Series markAsUnfeatured(Integer seriesId) {
        Series series = getSeriesById(seriesId);
        series.setIsFeatured(false);
        return seriesRepository.save(series);
    }
}
