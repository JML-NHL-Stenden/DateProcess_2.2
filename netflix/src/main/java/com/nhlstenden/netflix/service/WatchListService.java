package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.WatchList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nhlstenden.netflix.repository.WatchListRepository;

import java.util.List;

@Service
public class WatchListService {

    @Autowired
    private WatchListRepository watchListRepository;

    public WatchList addToWatchList(WatchList watchList) {
        return watchListRepository.save(watchList);
    }

    public List<WatchList> getWatchListByProfileId(Long profileId) {
        return watchListRepository.findByProfileId(profileId);
    }

    @Transactional
    public void removeFromWatchList(Long watchListId) {
        watchListRepository.deleteById(watchListId);
    }

    public void removeMovieFromWatchList(Long profileId, Long movieId) {
        watchListRepository.deleteByProfileIdAndMovieId(profileId, movieId);
    }

    public void removeEpisodeFromWatchList(Long profileId, Long episodeId) {
        watchListRepository.deleteByProfileIdAndEpisodeId(profileId, episodeId);
    }

    public boolean isMovieInWatchList(Long profileId, Long movieId) {
        return watchListRepository.findByProfileIdAndMovieId(profileId, movieId).isPresent();
    }

    public boolean isEpisodeInWatchList(Long profileId, Long episodeId) {
        return watchListRepository.findByProfileIdAndEpisodeId(profileId, episodeId).isPresent();
    }
}