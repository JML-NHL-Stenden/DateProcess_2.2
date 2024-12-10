package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Subtitle")
public class Subtitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtitle_id")
    private Long subtitleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id")
    private Episode episode;

    @Column(name = "language")
    private String language;

    @Column(name = "subtitle_location")
    private String subtitleLocation;
}