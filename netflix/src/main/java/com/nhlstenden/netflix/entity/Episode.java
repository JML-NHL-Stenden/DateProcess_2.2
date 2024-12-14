package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "episode_id")
    private Integer episodeId;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "season_id", nullable = false)
    private Integer seasonId;

    // Constructors
    public Episode() {
    }

    public Episode(LocalTime duration, String title, Integer seasonId) {
        this.duration = duration;
        this.title = title;
        this.seasonId = seasonId;
    }

    // Getters and Setters
    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }
}
