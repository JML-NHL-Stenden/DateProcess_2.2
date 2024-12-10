package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    Page<Series> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<Series> findByTotalSeasonsGreaterThan(Integer seasons);

    @Query("SELECT s FROM Series s JOIN s.genres g WHERE g.genreId = :genreId")
    List<Series> findByGenreId(Long genreId);

    @Query("SELECT s FROM Series s JOIN s.qualities q WHERE q.qualityId = :qualityId")
    List<Series> findByQualityId(Long qualityId);

    @Query("SELECT s FROM Series s WHERE s.title LIKE %:keyword% OR s.description LIKE %:keyword%")
    List<Series> searchByKeyword(String keyword);
}