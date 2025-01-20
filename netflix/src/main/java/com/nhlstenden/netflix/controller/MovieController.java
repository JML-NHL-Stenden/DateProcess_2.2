package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Movie;
import com.nhlstenden.netflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
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
    public ResponseEntity<Object> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }
        return ResponseEntity.ok(movies); // 200 OK
    }

    // JSON Response
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAllMoviesAsJson()
    {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // XML Response
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Movie>> getAllMoviesAsXml()
    {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // CSV Response
    @GetMapping(value = "/csv", produces = "text/csv")
    public void getAllMoviesAsCsv(HttpServletResponse response) throws IOException
    {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=movies.csv");

        List<Movie> movies = movieService.getAllMovies();

        try (PrintWriter writer = response.getWriter())
        {
            // Write CSV header
            writer.println("Movie ID,Title,Duration,Description,Featured");

            // Write CSV rows
            for (Movie movie : movies)
            {
                writer.println(String.format("%d,%s,%s,%s,%b",
                        movie.getMovieId(),
                        movie.getTitle(),
                        movie.getDuration(),
                        movie.getDescription(),
                        movie.getIsFeatured()));
            }
        }
    }
}
