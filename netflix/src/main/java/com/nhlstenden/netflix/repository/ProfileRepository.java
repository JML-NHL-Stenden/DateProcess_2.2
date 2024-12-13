package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>
{
    Profile findByProfileId(Integer profileId);

    boolean existsByProfileId(Integer profileId);
}
