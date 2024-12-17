package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "season")
public class Season
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private Integer seasonId;

    @Column(name = "season_number", nullable = false)
    private Integer seasonNumber;

    @Column(name = "series_id", nullable = false)
    private Integer seriesId;

    // Constructors
    public Season()
    {
    }

    public Season(Integer seasonNumber, Integer seriesId)
    {
        this.seasonNumber = seasonNumber;
        this.seriesId = seriesId;
    }

    // Getters and Setters
    public Integer getSeasonId()
    {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId)
    {
        this.seasonId = seasonId;
    }

    public Integer getSeasonNumber()
    {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber)
    {
        this.seasonNumber = seasonNumber;
    }

    public Integer getSeriesId()
    {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId)
    {
        this.seriesId = seriesId;
    }
}