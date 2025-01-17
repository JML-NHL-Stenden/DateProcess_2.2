package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Genre;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService
{

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository)
    {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres()
    {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Integer genreId)
    {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre with ID '" + genreId + "' does not exist in the database"));
    }

    public Genre getGenreByName(String genreName)
    {
        return genreRepository.findByGenreName(genreName)
                .orElseThrow(() -> new ResourceNotFoundException("Genre with name '" + genreName + "' does not exist in the database"));
    }

    public Genre createGenre(Genre genre)
    {
        if (genreRepository.findByGenreName(genre.getGenreName()).isPresent())
        {
            throw new IllegalArgumentException("Genre already exists with name: " + genre.getGenreName());
        }
        return genreRepository.save(genre);
    }

    public Genre updateGenre(Integer genreId, Genre genre)
    {
        Genre existingGenre = getGenreById(genreId);
        genre.setGenreId(existingGenre.getGenreId());
        return genreRepository.save(genre);
    }

    public void deleteGenre(Integer genreId)
    {
        Genre genre = getGenreById(genreId);
        genreRepository.deleteById(genre.getGenreId());
    }
}