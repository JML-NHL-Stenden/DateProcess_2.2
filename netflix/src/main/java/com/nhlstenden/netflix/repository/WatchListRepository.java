package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Long> {
    List<WatchList> findByProfileId(Long profileId);
    Optional<WatchList> findByProfileIdAndMovieId(Long profileId, Long movieId);
    Optional<WatchList> findByProfileIdAndEpisodeId(Long profileId, Long episodeId);
    void deleteByProfileIdAndMovieId(Long profileId, Long movieId);
    void deleteByProfileIdAndEpisodeId(Long profileId, Long episodeId);
}