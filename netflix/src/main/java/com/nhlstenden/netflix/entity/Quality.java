package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Quality")
public class Quality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quality_id")
    private Long qualityId;

    @Column(name = "quality")
    private String quality;

    @ManyToMany(mappedBy = "qualities")
    private List<Movie> movies;

    @ManyToMany(mappedBy = "qualities")
    private List<Series> series;
}