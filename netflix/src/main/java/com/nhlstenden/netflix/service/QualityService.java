package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Quality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhlstenden.netflix.repository.QualityRepository;

import java.util.List;

@Service
public class QualityService {

    @Autowired
    private QualityRepository qualityRepository;

    public Quality createQuality(Quality quality) {
        if (qualityRepository.existsByQuality(quality.getQuality())) {
            throw new RuntimeException("Quality already exists");
        }
        return qualityRepository.save(quality);
    }

    public Quality getQualityById(Long id) {
        return qualityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quality not found"));
    }

    public List<Quality> getAllQualities() {
        return qualityRepository.findAll();
    }

    public Quality updateQuality(Long id, Quality qualityDetails) {
        Quality quality = getQualityById(id);
        quality.setQuality(qualityDetails.getQuality());
        return qualityRepository.save(quality);
    }

    public void deleteQuality(Long id) {
        qualityRepository.deleteById(id);
    }

    public Quality findByQuality(String quality) {
        return qualityRepository.findByQuality(quality)
                .orElseThrow(() -> new RuntimeException("Quality not found"));
    }
}