package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Subscription;
import com.nhlstenden.netflix.service.SubscriptionService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>
{
    Subscription findBySubscriptionId(int id);

    boolean existsBySubscriptionId(int id);
}
