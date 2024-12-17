package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "genre")
public class Genre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "genre_name", nullable = false)
    private String genreName;

    // Constructors
    public Genre()
    {
    }

    public Genre(String genreName)
    {
        this.genreName = genreName;
    }

    // Getters and Setters
    public Integer getGenreId()
    {
        return genreId;
    }

    public void setGenreId(Integer genreId)
    {
        this.genreId = genreId;
    }

    public String getGenreName()
    {
        return genreName;
    }

    public void setGenreName(String genreName)
    {
        this.genreName = genreName;
    }
}