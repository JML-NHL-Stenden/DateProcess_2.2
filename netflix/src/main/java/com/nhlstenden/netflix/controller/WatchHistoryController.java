package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.entity.WatchHistory;
import com.nhlstenden.netflix.service.WatchHistoryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watch-history")
public class WatchHistoryController
{
    private final WatchHistoryService watchHistoryService;
    private final EntityManager entityManager;

    @Autowired
    public WatchHistoryController(WatchHistoryService watchHistoryService, EntityManager entityManager)
    {
        this.watchHistoryService = watchHistoryService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<WatchHistory>> getAllWatchHistories()
    {
        return ResponseEntity.ok(watchHistoryService.getAllWatchHistories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchHistory> getWatchHistory(@PathVariable Integer id)
    {
        return ResponseEntity.ok(watchHistoryService.getWatchHistory(id));
    }

    @PostMapping
    public ResponseEntity<WatchHistory> createWatchHistory(@RequestBody WatchHistory watchHistory)
    {
        return ResponseEntity.ok(watchHistoryService.createWatchHistory(watchHistory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchHistory> addOrUpdateProfile(@PathVariable Integer id, @RequestBody WatchHistory watchHistory)
    {
        WatchHistory result = watchHistoryService.addOrUpdateWatchHistory(id, watchHistory);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchHistory(@PathVariable Integer id)
    {
        watchHistoryService.deleteWatchHistory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<WatchHistory>> getAllWatchHistoryDirect()
    {
        Query query = entityManager.createNativeQuery("SELECT * FROM watch_history", WatchHistory.class);
        @SuppressWarnings("unchecked")
        List<WatchHistory> watchHistories = (List<WatchHistory>) query.getResultList();
        return ResponseEntity.ok(watchHistories);
    }
}
