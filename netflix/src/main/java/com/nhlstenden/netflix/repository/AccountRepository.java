package com.nhlstenden.Netflix.repository;

import com.nhlstenden.Netflix.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmail(String email);
    boolean existsByEmail(String email);
}