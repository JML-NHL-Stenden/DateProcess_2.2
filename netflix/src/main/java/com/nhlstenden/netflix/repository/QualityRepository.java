package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualityRepository extends JpaRepository<Quality, Integer>
{
    Optional<Quality> findByQuality(String quality);
}