package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findBySeriesId(Long seriesId);
    Season findBySeriesIdAndSeasonNumber(Long seriesId, Integer seasonNumber);
    boolean existsBySeriesIdAndSeasonNumber(Long seriesId, Integer seasonNumber);
}