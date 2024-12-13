package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "series")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Integer seriesId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "total_seasons", nullable = false)
    private Integer totalSeasons;

    @Column(name = "is_featured", nullable = false)
    private Boolean isFeatured = false;

    // Constructors
    public Series() {}

    public Series(String title, String description, Integer totalSeasons, Boolean isFeatured) {
        this.title = title;
        this.description = description;
        this.totalSeasons = totalSeasons;
        this.isFeatured = isFeatured;
    }

    // Getters and Setters
    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }
}
