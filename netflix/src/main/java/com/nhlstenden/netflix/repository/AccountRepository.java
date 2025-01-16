package com.nhlstenden.netflix.repository;

import com.nhlstenden.netflix.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // Find account by email
    Optional<Account> findByEmail(String email);

    // Find all accounts by role
    List<Account> findAllByRole(String role);
}
