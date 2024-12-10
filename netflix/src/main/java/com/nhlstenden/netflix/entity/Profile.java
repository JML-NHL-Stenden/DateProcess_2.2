package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "profile_age")
    private Integer profileAge;

    @Column(name = "is_profile_child")
    private Boolean isProfileChild;

    @Column(name = "language")
    private String language;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<WatchList> watchList;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<WatchHistory> watchHistory;
}