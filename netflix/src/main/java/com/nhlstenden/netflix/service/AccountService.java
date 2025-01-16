package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Fetch all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Fetch account by email
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    // Fetch account by ID
    public Optional<Account> getAccountById(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    // Create a new account
    public Account createAccount(Account account) {
        logger.info("Creating account for email: {}", account.getEmail());
        account.setPassword(passwordEncoder.encode(account.getPassword())); // Hash the password
        account.setRole(account.getRole() != null ? account.getRole() : "USER"); // Default role
        account.setPaymentMethod(null); // Set payment method
        account.setIsBlocked(false);
        account.setRestoreToken(null);
        account.setAccountCreatedDate(new java.sql.Timestamp(System.currentTimeMillis()));
        account.setInitDurationEnd(null);
        account.setPasswordMethod(null);
        account.setPaymentAccountString(null);
        return accountRepository.save(account);
    }

    // Update an existing account
    public Account updateAccount(String email, Account updatedAccount) {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setPassword(passwordEncoder.encode(updatedAccount.getPassword())); // Hash the password
            account.setPaymentMethod(updatedAccount.getPaymentMethod()); // Update payment method
            account.setIsBlocked(updatedAccount.getIsBlocked());
            account.setRestoreToken(updatedAccount.getRestoreToken());
            account.setInitDurationEnd(updatedAccount.getInitDurationEnd());
            account.setPasswordMethod(updatedAccount.getPasswordMethod());
            account.setPaymentAccountString(updatedAccount.getPaymentAccountString());
            account.setRole(updatedAccount.getRole()); // Update role if provided
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }

    // Delete an account
    public void deleteAccount(String email) {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        existingAccount.ifPresent(accountRepository::delete);
    }

    // Block an account
    public void blockAccount(String email) {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setIsBlocked(true);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }

    // Unblock an account
    public void unblockAccount(String email) {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent()) {
            Account account = existingAccount.get();
            account.setIsBlocked(false);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }

    // Authenticate an account
    public boolean authenticate(String email, String password) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return passwordEncoder.matches(password, account.getPassword()); // Secure comparison
        }
        return false;
    }

    // Fetch accounts by role
    public List<Account> getAccountsByRole(String role) {
        return accountRepository.findAllByRole(role);
    }
}
