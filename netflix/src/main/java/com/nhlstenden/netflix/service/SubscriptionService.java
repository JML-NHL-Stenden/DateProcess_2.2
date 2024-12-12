package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Subscription;
import com.nhlstenden.netflix.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService
{

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository)
    {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getAllSubscriptions()
    {
        return subscriptionRepository.findAll();
    }

    public Subscription getSubscriptionById(int id)
    {
        Subscription subscription = subscriptionRepository.findBySubscriptionId(id);
        if (subscription == null)
        {
            throw new IllegalArgumentException("Subscription not found with id: " + id);
        }
        return subscription;
    }

    public Subscription createSubscription(Subscription subscription)
    {
        if (subscriptionRepository.existsBySubscriptionId(subscription.getSubscriptionId()))
        {
            throw new IllegalArgumentException("Subscription already exists with id: " + subscription.getSubscriptionId());
        }
        return subscriptionRepository.save(subscription);
    }
}