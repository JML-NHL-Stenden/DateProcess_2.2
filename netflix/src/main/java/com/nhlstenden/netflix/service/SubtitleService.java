package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Subtitle;
import com.nhlstenden.netflix.repository.SubtitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtitleService
{
    private final SubtitleRepository subtitleRepository;

    @Autowired
    public SubtitleService(SubtitleRepository subtitleRepository)
    {
        this.subtitleRepository = subtitleRepository;
    }

    public List<Subtitle> getAllSubtitle()
    {
        return subtitleRepository.findAll();
    }

    public Subtitle getSubtitle(String subtitleLanguage)
    {
        Subtitle subtitle = subtitleRepository.findByLanguage(subtitleLanguage);
        if (subtitle == null)
        {
            throw new IllegalArgumentException("Subtitle not found with language: " + subtitleLanguage);
        }
        return subtitle;
    }

    public Subtitle createSubtitle(Subtitle subtitle)
    {
        if (subtitleRepository.existsByLanguage(subtitle.getLanguage()))
        {
            throw new IllegalArgumentException("Subtitle already exists with language: " + subtitle.getLanguage());
        }
        return subtitleRepository.save(subtitle);
    }

    public Subtitle addOrUpdateSubtitle(String language, Subtitle subtitle)
    {
        Subtitle existingSubtitle = subtitleRepository.findByLanguage(language);
        if (existingSubtitle != null)
        {
            subtitle.setLanguage(existingSubtitle.getLanguage());
        }
        return subtitleRepository.save(subtitle);
    }

    public void deleteSubtitle(String language)
    {
        Subtitle subtitle = getSubtitle(language);
        subtitleRepository.deleteById(subtitle.getLanguage());
    }
}
