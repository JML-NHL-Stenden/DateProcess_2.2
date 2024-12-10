package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenreName(String genreName);
    List<Genre> findByGenreNameContainingIgnoreCase(String keyword);
    boolean existsByGenreName(String genreName);
}