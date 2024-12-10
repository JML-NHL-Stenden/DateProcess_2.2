package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.WatchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhlstenden.netflix.repository.WatchHistoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WatchHistoryService {

    @Autowired
    private WatchHistoryRepository watchHistoryRepository;

    public WatchHistory addToHistory(WatchHistory watchHistory) {
        watchHistory.setWatchDateTime(LocalDateTime.now());
        return watchHistoryRepository.save(watchHistory);
    }

    public List<WatchHistory> getHistoryByProfileId(Long profileId) {
        return watchHistoryRepository.findByProfileIdOrderByWatchDateTimeDesc(profileId);
    }

    public void deleteHistoryEntry(Long id) {
        watchHistoryRepository.deleteById(id);
    }

    public void clearProfileHistory(Long profileId) {
        List<WatchHistory> history = watchHistoryRepository.findByProfileId(profileId);
        watchHistoryRepository.deleteAll(history);
    }

    public List<WatchHistory> getFrequentlyWatched(Integer minTimes) {
        return watchHistoryRepository.findByTimesWatchedGreaterThan(minTimes);
    }

    public WatchHistory updateWatchCount(Long id) {
        WatchHistory history = watchHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("History entry not found"));
        history.setTimesWatched(history.getTimesWatched() + 1);
        return watchHistoryRepository.save(history);
    }
}