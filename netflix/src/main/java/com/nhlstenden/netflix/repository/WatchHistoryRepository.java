package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Integer>
{
    WatchHistory findByWatchHistoryId(Integer watchHistoryId);

    boolean existsByWatchHistoryId(Integer watchHistoryId);
}
