package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quality")
public class Quality
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quality_id")
    private Integer qualityId;

    @Column(name = "quality", nullable = false, length = 255)
    private String quality;

    // Constructors
    public Quality()
    {
    }

    public Quality(String quality)
    {
        this.quality = quality;
    }

    // Getters and Setters
    public Integer getQualityId()
    {
        return qualityId;
    }

    public void setQualityId(Integer qualityId)
    {
        this.qualityId = qualityId;
    }

    public String getQuality()
    {
        return quality;
    }

    public void setQuality(String quality)
    {
        this.quality = quality;
    }
}