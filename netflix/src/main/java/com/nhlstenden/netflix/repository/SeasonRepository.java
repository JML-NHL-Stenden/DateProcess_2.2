package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer>
{
    List<Season> findBySeriesId(Integer seriesId);
    Optional<Season> findBySeriesIdAndSeasonNumber(Integer seriesId, Integer seasonNumber);
}