package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Genre;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Fetch all genres
    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        if (genres.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(genres);
    }

    // Fetch genre by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getGenreById(@PathVariable Integer id) {
        try {
            Genre genre = genreService.getGenreById(id);
            return ResponseEntity.ok(genre);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Fetch genre by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getGenreByName(@PathVariable("name") String genreName) {
        try {
            Genre genre = genreService.getGenreByName(genreName);
            return ResponseEntity.ok(genre);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Create a new genre
    @PostMapping
    public ResponseEntity<Object> createGenre(@RequestBody Genre genre) {
        try {
            Genre createdGenre = genreService.createGenre(genre);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Update an existing genre
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGenre(@PathVariable("id") Integer genreId, @RequestBody Genre genre) {
        try {
            Genre updatedGenre = genreService.updateGenre(genreId, genre);
            return ResponseEntity.ok(updatedGenre);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Delete a genre
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable("id") Integer genreId) {
        try {
            genreService.deleteGenre(genreId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage())); // 404 Not Found
        }
    }
}
