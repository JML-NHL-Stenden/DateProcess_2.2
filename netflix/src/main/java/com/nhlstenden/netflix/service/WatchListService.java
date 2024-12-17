package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.WatchList;
import com.nhlstenden.netflix.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchListService
{
    private final WatchListRepository watchListRepository;

    @Autowired
    public WatchListService(WatchListRepository watchListRepository)
    {
        this.watchListRepository = watchListRepository;
    }

    public List<WatchList> getAllWatchLists()
    {
        return watchListRepository.findAll();
    }

    public WatchList getWatchList(Integer watchListId)
    {
        WatchList watchList = watchListRepository.findByWatchListId(watchListId);
        if (watchList == null)
        {
            throw new IllegalArgumentException("WatchList not found with id: " + watchListId);
        }
        return watchList;
    }

    public WatchList createWatchList(WatchList watchList)
    {
        if (watchListRepository.existsByWatchListId(watchList.getWatchListId()))
        {
            throw new IllegalArgumentException("WatchList already exists with id: " + watchList.getWatchListId());
        }
        return watchListRepository.save(watchList);
    }

    public WatchList addOrUpdateWatchList(Integer id, WatchList watchList)
    {
        WatchList existingWatchList = watchListRepository.findById(id).orElse(null);
        if (existingWatchList != null)
        {
            watchList.setWatchListId(existingWatchList.getWatchListId());
        }
        return watchListRepository.save(watchList);
    }

    public void deleteWatchList(Integer id)
    {
        WatchList watchList = getWatchList(id);
        watchListRepository.deleteById(watchList.getWatchListId());
    }
}
