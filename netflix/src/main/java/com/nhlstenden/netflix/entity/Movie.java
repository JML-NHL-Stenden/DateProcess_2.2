package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "duration")
    private java.sql.Time duration;

    @Column(name = "description")
    private String description;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    // Constructors
    public Movie()
    {
    }

    public Movie(String title, java.sql.Time duration, String description, Boolean isFeatured)
    {
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.isFeatured = isFeatured;
    }

    // Getters and Setters
    public Integer getMovieId()
    {
        return movieId;
    }

    public void setMovieId(Integer movieId)
    {
        this.movieId = movieId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public java.sql.Time getDuration()
    {
        return duration;
    }

    public void setDuration(java.sql.Time duration)
    {
        this.duration = duration;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getIsFeatured()
    {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured)
    {
        this.isFeatured = isFeatured;
    }
}
