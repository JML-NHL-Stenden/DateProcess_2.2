package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Subtitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitle, String>
{
    Subtitle findByLanguage(String language);
    boolean existsByLanguage(String language);
}
