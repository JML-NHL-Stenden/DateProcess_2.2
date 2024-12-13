package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Profile
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "profile_age")
    private Integer profileAge;

    @Column(name = "language")
    private String preferredLanguage;

    @Column(name = "is_profile_child")
    private Boolean isProfileChild;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Constructors
    public Profile()
    {
    }

    public Profile(String profileName, String profileImage, Integer profileAge, String preferredLanguage,
            Boolean isProfileChild, Account account)
    {
        this.profileName = profileName;
        this.profileImage = profileImage;
        this.profileAge = profileAge;
        this.preferredLanguage = preferredLanguage;
        this.isProfileChild = isProfileChild;
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

    public Boolean getProfileChild()
    {
        return this.isProfileChild;
    }

    public void setProfileChild(Boolean profileChild)
    {
        isProfileChild = profileChild;
    }

    public String getPreferredLanguage()
    {
        return this.preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage)
    {
        this.preferredLanguage = preferredLanguage;
    }

    public Integer getProfileAge()
    {
        return this.profileAge;
    }

    public void setProfileAge(Integer profileAge)
    {
        this.profileAge = profileAge;
    }

    public Integer getProfileId()
    {
        return this.profileId;
    }

    public void setProfileId(Integer profileId)
    {
        this.profileId = profileId;
    }

    public String getProfileImage()
    {
        return this.profileImage;
    }

    public void setProfileImage(String profileImage)
    {
        this.profileImage = profileImage;
    }

    public String getProfileName()
    {
        return this.profileName;
    }

    public void setProfileName(String profileName)
    {
        this.profileName = profileName;
    }
}
