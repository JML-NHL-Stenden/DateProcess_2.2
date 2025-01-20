package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Subscription;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.service.SubscriptionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable int id) {
        try {
            return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Subscription> createSubscription(Subscription subscription)
    {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }
}
