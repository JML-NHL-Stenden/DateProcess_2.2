package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>
{
    Optional<Genre> findByGenreName(String genreName);
}