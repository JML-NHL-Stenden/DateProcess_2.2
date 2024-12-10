package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByAccountId(Long accountId);
    List<Profile> findByIsProfileChild(Boolean isProfileChild);
    List<Profile> findByAccountIdAndIsProfileChild(Long accountId, Boolean isProfileChild);
    boolean existsByAccountIdAndProfileName(Long accountId, String profileName);
}