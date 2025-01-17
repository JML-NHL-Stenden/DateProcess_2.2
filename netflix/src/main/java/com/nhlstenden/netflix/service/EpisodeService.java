package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Episode;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService
{

    private final EpisodeRepository episodeRepository;

    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository)
    {
        this.episodeRepository = episodeRepository;
    }

    public List<Episode> getAllEpisodes()
    {
        return episodeRepository.findAll();
    }

    public Episode getEpisodeById(Integer episodeId)
    {
        return episodeRepository.findById(episodeId)
                .orElseThrow(() -> new ResourceNotFoundException("Episode with ID '" + episodeId + "' does not exist in the database"));
    }

    public Episode createEpisode(Episode episode)
    {
        return episodeRepository.save(episode);
    }

    public Episode updateEpisode(Integer episodeId, Episode episode)
    {
        Episode existingEpisode = getEpisodeById(episodeId);
        episode.setEpisodeId(existingEpisode.getEpisodeId());
        return episodeRepository.save(episode);
    }

    public void deleteEpisode(Integer episodeId)
    {
        Episode episode = getEpisodeById(episodeId);
        episodeRepository.deleteById(episode.getEpisodeId());
    }
}
