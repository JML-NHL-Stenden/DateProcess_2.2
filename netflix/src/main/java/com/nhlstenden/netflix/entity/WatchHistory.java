package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "watch_history")
public class WatchHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watch_history_id")
    private Integer watchHistoryId;

    @Column(name = "times_watched")
    private Integer timesWatched;

    @Column(name = "watch_date_time")
    private LocalDateTime watchDateTime;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // Constructors
    public WatchHistory()
    {
    }

    public WatchHistory(Integer timesWatched, LocalDateTime watchDateTime, Profile profile)
    {
        this.timesWatched = timesWatched;
        this.watchDateTime = watchDateTime;
        this.profile = profile;
    }

    // Getters and Setters

    public Profile getProfile()
    {
        return this.profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    public Integer getTimesWatched()
    {
        return this.timesWatched;
    }

    public void setTimesWatched(Integer timesWatched)
    {
        this.timesWatched = timesWatched;
    }

    public LocalDateTime getWatchDateTime()
    {
        return this.watchDateTime;
    }

    public void setWatchDateTime(LocalDateTime watchDateTime)
    {
        this.watchDateTime = watchDateTime;
    }

    public Integer getWatchHistoryId()
    {
        return this.watchHistoryId;
    }

    public void setWatchHistoryId(Integer watchHistoryId)
    {
        this.watchHistoryId = watchHistoryId;
    }
}
