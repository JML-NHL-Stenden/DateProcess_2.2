package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer>
{
    Optional<Episode> findByTitle(String title);
}
