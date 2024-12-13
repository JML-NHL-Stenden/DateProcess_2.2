package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {
    List<Series> findByTitleContainingIgnoreCase(String title);
    List<Series> findByIsFeatured(Boolean isFeatured);
}
