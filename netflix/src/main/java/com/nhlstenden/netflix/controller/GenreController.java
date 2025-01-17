package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Genre;
import com.nhlstenden.netflix.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController
{

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService)
    {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres()
    {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Integer genreId)
    {
        return ResponseEntity.ok(genreService.getGenreById(genreId));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Genre> getGenreByName(@PathVariable("name") String genreName)
    {
        return ResponseEntity.ok(genreService.getGenreByName(genreName));
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre)
    {
        return ResponseEntity.ok(genreService.createGenre(genre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable("id") Integer genreId, @RequestBody Genre genre)
    {
        return ResponseEntity.ok(genreService.updateGenre(genreId, genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Integer genreId)
    {
        genreService.deleteGenre(genreId);
        return ResponseEntity.noContent().build();
    }
}