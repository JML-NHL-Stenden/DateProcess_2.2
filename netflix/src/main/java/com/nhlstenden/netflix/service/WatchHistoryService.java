package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.WatchHistory;
import com.nhlstenden.netflix.repository.WatchHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchHistoryService
{
    private final WatchHistoryRepository watchHistoryRepository;

    @Autowired
    public WatchHistoryService(WatchHistoryRepository watchHistoryRepository)
    {
        this.watchHistoryRepository = watchHistoryRepository;
    }

    public List<WatchHistory> getAllWatchHistories()
    {
        return watchHistoryRepository.findAll();
    }

    public WatchHistory getWatchHistory(Integer id)
    {
        WatchHistory watchHistory = watchHistoryRepository.findByWatchHistoryId(id);
        if (watchHistory == null)
        {
            throw new EntityNotFoundException("WatchHistory not found with id: " + id);
        }
        return watchHistory;
    }

    public WatchHistory createWatchHistory(WatchHistory watchHistory)
    {
        if (watchHistoryRepository.existsByWatchHistoryId(watchHistory.getWatchHistoryId()))
        {
            throw new IllegalArgumentException("WatchHistory already exists with id: " + watchHistory.getWatchHistoryId());
        }
        return watchHistoryRepository.save(watchHistory);
    }

    public WatchHistory addOrUpdateWatchHistory(Integer id, WatchHistory watchHistory)
    {
        WatchHistory existingWatchHistory = watchHistoryRepository.findById(id).orElse(null);
        if (existingWatchHistory != null)
        {
            watchHistory.setWatchHistoryId(existingWatchHistory.getWatchHistoryId());
        }
        return watchHistoryRepository.save(watchHistory);
    }

    public void deleteWatchHistory(Integer id)
    {
        WatchHistory watchHistory = getWatchHistory(id);
        watchHistoryRepository.deleteById(watchHistory.getWatchHistoryId());
    }
}
