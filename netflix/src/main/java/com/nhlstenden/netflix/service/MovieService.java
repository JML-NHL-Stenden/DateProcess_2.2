package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Movie;
import com.nhlstenden.netflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with ID " + movieId + " not found."));
    }

    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> getMoviesByFeaturedStatus(Boolean isFeatured) {
        return movieRepository.findByIsFeatured(isFeatured);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer movieId, Movie updatedMovie) {
        Movie existingMovie = getMovieById(movieId);
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setDuration(updatedMovie.getDuration());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setIsFeatured(updatedMovie.getIsFeatured());
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Integer movieId) {
        Movie movie = getMovieById(movieId);
        movieRepository.delete(movie);
    }

    public Movie markAsFeatured(Integer movieId) {
        Movie movie = getMovieById(movieId);
        movie.setIsFeatured(true);
        return movieRepository.save(movie);
    }

    public Movie markAsUnfeatured(Integer movieId) {
        Movie movie = getMovieById(movieId);
        movie.setIsFeatured(false);
        return movieRepository.save(movie);
    }
}
