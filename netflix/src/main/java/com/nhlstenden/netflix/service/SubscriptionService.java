package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhlstenden.netflix.repository.SubscriptionRepository;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    public Subscription getSubscriptionByAccountId(Long accountId) {
        return subscriptionRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Subscription not found for account"));
    }

    public List<Subscription> getSubscriptionsByType(String type) {
        return subscriptionRepository.findBySubscriptionType(type);
    }

    public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
        Subscription subscription = getSubscriptionById(id);
        subscription.setSubscriptionType(subscriptionDetails.getSubscriptionType());
        subscription.setSubscriptionPrice(subscriptionDetails.getSubscriptionPrice());
        subscription.setDiscount(subscriptionDetails.getDiscount());
        return subscriptionRepository.save(subscription);
    }

    public void cancelSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    public boolean hasActiveSubscription(Long accountId) {
        return subscriptionRepository.existsByAccountId(accountId);
    }

    public List<Subscription> getSubscriptionsByMaxPrice(Double maxPrice) {
        return subscriptionRepository.findBySubscriptionPriceLessThanEqual(maxPrice);
    }
}