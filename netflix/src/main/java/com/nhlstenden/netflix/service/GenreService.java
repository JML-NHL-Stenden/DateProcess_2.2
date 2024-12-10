package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhlstenden.netflix.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre createGenre(Genre genre) {
        if (genreRepository.existsByGenreName(genre.getGenreName())) {
            throw new RuntimeException("Genre already exists");
        }
        return genreRepository.save(genre);
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre updateGenre(Long id, Genre genreDetails) {
        Genre genre = getGenreById(id);
        genre.setGenreName(genreDetails.getGenreName());
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    public Genre findByName(String name) {
        return genreRepository.findByGenreName(name)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
    }

    public List<Genre> searchGenres(String keyword) {
        return genreRepository.findByGenreNameContainingIgnoreCase(keyword);
    }
}