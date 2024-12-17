package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table
public class WatchList
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watchlist_id")
    private Integer watchListId;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;

    // Constructors
    public WatchList()
    {
    }

    public WatchList(Profile profile, Movie movie, Episode episode)
    {
        this.profile = profile;
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

    public Movie getMovie()
    {
        return this.movie;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }

    public Profile getProfile()
    {
        return this.profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public Integer getWatchListId()
    {
        return this.watchListId;
    }

    public void setWatchListId(Integer watchListId)
    {
        this.watchListId = watchListId;
    }
}
