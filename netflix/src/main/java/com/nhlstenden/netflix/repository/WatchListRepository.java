package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Integer>
{
    WatchList findByWatchListId(Integer watchListId);

    boolean existsByWatchListId(Integer watchListId);
}
