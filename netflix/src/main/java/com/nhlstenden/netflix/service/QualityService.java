package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Quality;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.repository.QualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualityService
{
    private final QualityRepository qualityRepository;

    @Autowired
    public QualityService(QualityRepository qualityRepository)
    {
        this.qualityRepository = qualityRepository;
    }

    public List<Quality> getAllQualities()
    {
        return qualityRepository.findAll();
    }

    public Quality getQualityById(Integer qualityId)
    {
        return qualityRepository.findById(qualityId)
                .orElseThrow(() -> new ResourceNotFoundException("Quality with ID '" + qualityId + "' does not exist in the database"));
    }

    public Quality getQualityByName(String quality)
    {
        return qualityRepository.findByQuality(quality)
                .orElseThrow(() -> new ResourceNotFoundException("Quality with name '" + quality + "' does not exist in the database"));
    }

    public Quality createQuality(Quality quality)
    {
        if (qualityRepository.findByQuality(quality.getQuality()).isPresent())
        {
            throw new IllegalArgumentException("Quality already exists with name: " + quality.getQuality());
        }
        return qualityRepository.save(quality);
    }

    public Quality updateQuality(Integer qualityId, Quality quality)
    {
        Quality existingQuality = getQualityById(qualityId);
        quality.setQualityId(existingQuality.getQualityId());
        return qualityRepository.save(quality);
    }

    public void deleteQuality(Integer qualityId)
    {
        Quality quality = getQualityById(qualityId);
        qualityRepository.deleteById(quality.getQualityId());
    }
}