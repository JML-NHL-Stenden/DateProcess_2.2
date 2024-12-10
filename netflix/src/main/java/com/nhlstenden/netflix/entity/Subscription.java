package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "subscription_type")
    private String subscriptionType;

    @Column(name = "description")
    private String description;

    @Column(name = "subscription_price")
    private Double subscriptionPrice;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "user_invitation")
    private Boolean userInvitation;
}