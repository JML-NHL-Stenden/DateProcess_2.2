package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Profile;
import com.nhlstenden.netflix.entity.Subscription;
import com.nhlstenden.netflix.service.SubscriptionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController
{
    private final SubscriptionService subscriptionService;
    private final EntityManager entityManager;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, EntityManager entityManager)
    {
        this.subscriptionService = subscriptionService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions()
    {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/{subId}")
    public ResponseEntity<Subscription> getSubscription(int id)
    {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(Subscription subscription)
    {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }

    @PutMapping("/{subId}")
    public ResponseEntity<Subscription> addOrUpdateSubscription(@PathVariable Integer subscriptionId, @RequestBody Subscription subscription)
    {
        Subscription result = subscriptionService.addOrUpdateSubscription(subscriptionId, subscription);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Integer subscriptionId)
    {
        subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
