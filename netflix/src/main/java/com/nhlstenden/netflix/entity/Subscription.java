package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Integer subscriptionId;

    @Column(name = "subscription_type")
    private String subscriptionType;

    @Column(name = "subscription_price")
    private Double subscriptionPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "user_invitation")
    private Boolean userInvitation;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Constructors
    public Subscription()
    {
    }

    public Subscription(String subscriptionType, Double subscriptionPrice, String description,
                        Double discount, Boolean userInvitation, Account account)
    {
        this.subscriptionType = subscriptionType;
        this.subscriptionPrice = subscriptionPrice;
        this.description = description;
        this.discount = discount;
        this.userInvitation = userInvitation;
        this.account = account;
    }

    // Getters and Setters
    public Account getAccount()
    {
        return this.account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getDiscount()
    {
        return this.discount;
    }

    public void setDiscount(Double discount)
    {
        this.discount = discount;
    }

    public Integer getSubscriptionId()
    {
        return this.subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId)
    {
        this.subscriptionId = subscriptionId;
    }

    public Double getSubscriptionPrice()
    {
        return this.subscriptionPrice;
    }

    public void setSubscriptionPrice(Double subscriptionPrice)
    {
        this.subscriptionPrice = subscriptionPrice;
    }

    public String getSubscriptionType()
    {
        return this.subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType)
    {
        this.subscriptionType = subscriptionType;
    }

    public Boolean getUserInvitation()
    {
        return this.userInvitation;
    }

    public void setUserInvitation(Boolean userInvitation)
    {
        this.userInvitation = userInvitation;
    }
}