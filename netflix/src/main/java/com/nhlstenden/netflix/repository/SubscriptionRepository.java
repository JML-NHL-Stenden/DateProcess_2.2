package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByAccountId(Long accountId);
    List<Subscription> findBySubscriptionType(String subscriptionType);
    List<Subscription> findBySubscriptionPriceLessThanEqual(Double price);
    boolean existsByAccountId(Long accountId);
}