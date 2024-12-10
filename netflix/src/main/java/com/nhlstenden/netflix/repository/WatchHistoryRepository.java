package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findByProfileId(Long profileId);
    List<WatchHistory> findByProfileIdOrderByWatchDateTimeDesc(Long profileId);
    List<WatchHistory> findByTimesWatchedGreaterThan(Integer times);
}