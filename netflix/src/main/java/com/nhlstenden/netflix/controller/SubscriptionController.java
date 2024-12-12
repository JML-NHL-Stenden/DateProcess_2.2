package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Subscription;
import com.nhlstenden.netflix.service.SubscriptionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
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

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscription(int id)
    {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(Subscription subscription)
    {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }
}
