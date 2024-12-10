package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Subscription;
import com.nhlstenden.netflix.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@Tag(name = "Subscription Management", description = "Subscription-related operations")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    @Operation(summary = "Create new subscription")
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get subscription by ID")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Get subscription by account ID")
    public ResponseEntity<Subscription> getSubscriptionByAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByAccountId(accountId));
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get subscriptions by type")
    public ResponseEntity<List<Subscription>> getSubscriptionsByType(@PathVariable String type) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByType(type));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update subscription")
    public ResponseEntity<Subscription> updateSubscription(
            @PathVariable Long id,
            @Valid @RequestBody Subscription subscriptionDetails) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscriptionDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel subscription")
    public ResponseEntity<?> cancelSubscription(@PathVariable Long id) {
        subscriptionService.cancelSubscription(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account/{accountId}/status")
    @Operation(summary = "Check subscription status")
    public ResponseEntity<Boolean> hasActiveSubscription(@PathVariable Long accountId) {
        return ResponseEntity.ok(subscriptionService.hasActiveSubscription(accountId));
    }

    @GetMapping("/price")
    @Operation(summary = "Get subscriptions by max price")
    public ResponseEntity<List<Subscription>> getSubscriptionsByMaxPrice(
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByMaxPrice(maxPrice));
    }
}