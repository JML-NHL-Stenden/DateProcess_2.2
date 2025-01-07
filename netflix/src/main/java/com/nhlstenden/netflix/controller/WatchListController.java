package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.entity.WatchList;
import com.nhlstenden.netflix.service.WatchListService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchlist")
public class WatchListController
{
    private final WatchListService watchListService;
    private final EntityManager entityManager;

    @Autowired
    public WatchListController(WatchListService watchListService, EntityManager entityManager)
    {
        this.watchListService = watchListService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<WatchList>> getAllWatchLists()
    {
        return ResponseEntity.ok(watchListService.getAllWatchLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchList> getWatchList(@PathVariable Integer watchListId)
    {
        return ResponseEntity.ok(watchListService.getWatchList(watchListId));
    }

    @PostMapping
    public ResponseEntity<WatchList> createWatchList(@RequestBody WatchList watchList)
    {
        return ResponseEntity.ok(watchListService.createWatchList(watchList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchList> addOrUpdateWatchList(@PathVariable Integer watchListId, @RequestBody WatchList watchList)
    {
        WatchList result = watchListService.addOrUpdateWatchList(watchListId, watchList);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchList(@PathVariable Integer watchListId)
    {
        watchListService.deleteWatchList(watchListId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<WatchList>> getAllWatchListsDirect()
    {
        Query query = entityManager.createNativeQuery("SELECT * FROM watchList", WatchList.class);
        @SuppressWarnings("unchecked")
        List<WatchList> WatchLists = (List<WatchList>) query.getResultList();
        return ResponseEntity.ok(WatchLists);
    }

}
