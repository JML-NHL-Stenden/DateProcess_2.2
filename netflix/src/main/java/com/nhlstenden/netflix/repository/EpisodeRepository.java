package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findBySeasonId(Long seasonId);
    List<Episode> findBySeasonIdOrderByEpisodeId(Long seasonId);
    List<Episode> findByTitleContainingIgnoreCase(String title);
}