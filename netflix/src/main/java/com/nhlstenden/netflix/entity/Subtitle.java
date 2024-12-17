package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subtitle")
public class Subtitle
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtitle_id")
    private Integer subtitleId;

    @Column(name = "language")
    private String language;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;

    // Constructors
    public Subtitle()
    {
    }

    public Subtitle(String language, Movie movie, Episode episode)
    {
        this.language = language;
        this.movie = movie;
        this.episode = episode;
    }

    // Getters and Setters

    public Episode getEpisode()
    {
        return this.episode;
    }

    public void setEpisode(Episode episode)
    {
        this.episode = episode;
    }

    public String getLanguage()
    {
        return this.language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public Movie getMovie()
    {
        return this.movie;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }

    public Integer getSubtitleId()
    {
        return this.subtitleId;
    }

    public void setSubtitleId(Integer subtitleId)
    {
        this.subtitleId = subtitleId;
    }
}
