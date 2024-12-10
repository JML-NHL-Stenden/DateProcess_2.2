package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.genreId = :genreId")
    List<Movie> findByGenreId(Long genreId);

    @Query("SELECT m FROM Movie m JOIN m.qualities q WHERE q.qualityId = :qualityId")
    List<Movie> findByQualityId(Long qualityId);

    @Query(value = "SELECT m.* FROM Movie m " +
            "JOIN Watch_History_Movie whm ON m.movie_id = whm.movie_id " +
            "GROUP BY m.movie_id " +
            "ORDER BY COUNT(whm.watch_history_id) DESC LIMIT :limit",
            nativeQuery = true)
    List<Movie> findMostWatchedMovies(int limit);
}