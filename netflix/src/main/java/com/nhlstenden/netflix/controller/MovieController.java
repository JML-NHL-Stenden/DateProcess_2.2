package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Movie;
import com.nhlstenden.netflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController
{

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies()
    {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@RequestParam String title)
    {
        return ResponseEntity.ok(movieService.getMoviesByTitle(title));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Movie>> getFeaturedMovies(@RequestParam Boolean isFeatured)
    {
        return ResponseEntity.ok(movieService.getMoviesByFeaturedStatus(isFeatured));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie)
    {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie movie)
    {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id)
    {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/feature")
    public ResponseEntity<Movie> markAsFeatured(@PathVariable Integer id)
    {
        return ResponseEntity.ok(movieService.markAsFeatured(id));
    }

    @PatchMapping("/{id}/unfeature")
    public ResponseEntity<Movie> markAsUnfeatured(@PathVariable Integer id)
    {
        return ResponseEntity.ok(movieService.markAsUnfeatured(id));
    }
}
