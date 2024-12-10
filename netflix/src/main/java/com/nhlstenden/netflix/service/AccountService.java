package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nhlstenden.netflix.repository.AccountRepository;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account createAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setIsBlocked(false);
        account.setInitDurationEnd(LocalDateTime.now().plusMonths(1));
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Transactional
    public Account updateAccount(Long id, Account accountDetails) {
        Account account = getAccountById(id);
        account.setEmail(accountDetails.getEmail());
        if (accountDetails.getPassword() != null) {
            account.setPassword(passwordEncoder.encode(accountDetails.getPassword()));
        }
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        accountRepository.delete(account);
    }

    public void blockAccount(Long id) {
        Account account = getAccountById(id);
        account.setIsBlocked(true);
        accountRepository.save(account);
    }

    public void unblockAccount(Long id) {
        Account account = getAccountById(id);
        account.setIsBlocked(false);
        accountRepository.save(account);
    }

    public void resetPassword(String email, String newPassword) {
        Account account = getAccountByEmail(email);
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }
}