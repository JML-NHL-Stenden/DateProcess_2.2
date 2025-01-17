package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Movie;
import com.nhlstenden.netflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }
}
