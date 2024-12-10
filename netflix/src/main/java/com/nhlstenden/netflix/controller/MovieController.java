package com.nhlstenden.netflix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nhlstenden.netflix.service.MovieService;



@RestController
@RequestMapping("/api/v1/movies")
@Tag(name = "Movie Management", description = "Movie operations")
public class MovieController {
    @Autowired
    private MovieService movieService;

//    @PostMapping
//    @Operation(summary = "Add new movie")
//    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieCreationDTO dto) {
//        return ResponseEntity.ok(movieService.addMovie(dto));
//    }

//    @GetMapping
//    @Operation(summary = "Get all movies")
//    public ResponseEntity<Page<MovieDTO>> getAllMovies(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return ResponseEntity.ok(movieService.getAllMovies(page, size));
//    }

//    @GetMapping("/{movieId}")
//    @Operation(summary = "Get movie details")
//    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long movieId) {
//        return ResponseEntity.ok(movieService.getMovie(movieId));
//    }

//    @PatchMapping("/{movieId}")
//    @Operation(summary = "Update movie")
//    public ResponseEntity<MovieDTO> updateMovie(
//            @PathVariable Long movieId,
//            @RequestBody MovieUpdateDTO dto) {
//        return ResponseEntity.ok(movieService.updateMovie(movieId, dto));
//    }

    @DeleteMapping("/{movieId}")
    @Operation(summary = "Delete movie")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/search")
//    @Operation(summary = "Search movies")
//    public ResponseEntity<Page<MovieDTO>> searchMovies(
//            @RequestParam String query,
//            @RequestParam(required = false) List<String> genres,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return ResponseEntity.ok(movieService.searchMovies(query, genres, page, size));
//    }
}