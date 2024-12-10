package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Subtitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhlstenden.netflix.repository.SubtitleRepository;

import java.util.List;

@Service
public class SubtitleService {

    @Autowired
    private SubtitleRepository subtitleRepository;

    public Subtitle createSubtitle(Subtitle subtitle) {
        return subtitleRepository.save(subtitle);
    }

    public Subtitle getSubtitleById(Long id) {
        return subtitleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subtitle not found"));
    }

    public List<Subtitle> getSubtitlesByMovieId(Long movieId) {
        return subtitleRepository.findByMovieId(movieId);
    }

    public List<Subtitle> getSubtitlesByEpisodeId(Long episodeId) {
        return subtitleRepository.findByEpisodeId(episodeId);
    }

    public List<Subtitle> getSubtitlesByLanguage(String language) {
        return subtitleRepository.findByLanguage(language);
    }

    public Subtitle updateSubtitle(Long id, Subtitle subtitleDetails) {
        Subtitle subtitle = getSubtitleById(id);
        subtitle.setLanguage(subtitleDetails.getLanguage());
        subtitle.setSubtitleLocation(subtitleDetails.getSubtitleLocation());
        return subtitleRepository.save(subtitle);
    }

    public void deleteSubtitle(Long id) {
        subtitleRepository.deleteById(id);
    }
}