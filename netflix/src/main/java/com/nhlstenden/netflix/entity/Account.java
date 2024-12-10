package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "restore_token")
    private Integer restoreToken;

    @Column(name = "init_duration_end")
    private LocalDateTime initDurationEnd;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Profile> profiles;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;
}