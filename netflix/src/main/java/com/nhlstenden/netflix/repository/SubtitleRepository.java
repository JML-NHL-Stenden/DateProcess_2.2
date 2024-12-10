package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Subtitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitle, Long> {
    List<Subtitle> findByMovieId(Long movieId);
    List<Subtitle> findByEpisodeId(Long episodeId);
    List<Subtitle> findByLanguage(String language);
}