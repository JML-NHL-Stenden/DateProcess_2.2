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
public class AccountService
{

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder)
    {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Account> getAllAccounts()
    {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountByEmail(String email)
    {
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> getAccountById(Integer accountId)
    {
        return accountRepository.findById(accountId);
    }

    public Account createAccount(Account account)
    {
        logger.info("Creating account for email: {}", account.getEmail());

        // Check if account with the same email already exists
        if (accountRepository.findByEmail(account.getEmail()).isPresent())
        {
            throw new IllegalArgumentException("Account with email " + account.getEmail() + " already exists.");
        }

        // Hash the password before saving
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        // Set default role if none is provided
        if (account.getRole() == null || account.getRole().isEmpty())
        {
            account.setRole("USER");
        }

        // Initialize other optional fields
        account.setIsBlocked(false);
        account.setAccountCreatedDate(new java.sql.Timestamp(System.currentTimeMillis()));

        return accountRepository.save(account);
    }

    // Update - existing account
    public Account updateAccount(String email, Account updatedAccount)
    {
        Optional<Account> existingAccountOptional = accountRepository.findByEmail(email);

        if (existingAccountOptional.isPresent())
        {
            Account existingAccount = existingAccountOptional.get();

            // update account details
            if (updatedAccount.getPassword() != null)
            {
                existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
            }
            if (updatedAccount.getRole() != null)
            {
                existingAccount.setRole(updatedAccount.getRole());
            }
            if (updatedAccount.getIsBlocked() != null)
            {
                existingAccount.setIsBlocked(updatedAccount.getIsBlocked());
            }

            logger.info("Updating account for email: {}", email);
            return accountRepository.save(existingAccount);
        }
        else
        {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }

    public void deleteAccount(String email)
    {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent())
        {
            accountRepository.delete(existingAccount.get());
            logger.info("Account deleted: {}", email);
        }
        else
        {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }

    public void blockAccount(String email)
    {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent())
        {
            Account account = existingAccount.get();
            account.setIsBlocked(true);
            accountRepository.save(account);
            logger.info("Account blocked: {}", email);
        }
        else
        {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }

    // unblock an account
    public void unblockAccount(String email)
    {
        Optional<Account> existingAccount = accountRepository.findByEmail(email);
        if (existingAccount.isPresent())
        {
            Account account = existingAccount.get();
            account.setIsBlocked(false);
            accountRepository.save(account);
            logger.info("Account unblocked: {}", email);
        }
        else
        {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }

    public boolean authenticate(String email, String password)
    {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);

        if (accountOptional.isPresent())
        {
            Account account = accountOptional.get();

            // Debugging Logs
            logger.info("Stored hashed password: {}", account.getPassword());
            logger.info("Password to verify: {}", password);

            // Compare raw password to hashed password in the database
            boolean isMatch = passwordEncoder.matches(password, account.getPassword());
            logger.info("Password match result for {}: {}", email, isMatch);

            return isMatch;
        }

        logger.warn("Account not found for email: {}", email);
        return false;
    }

    public void changePassword(String email, String newPassword)
    {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);

        if (accountOptional.isPresent())
        {
            Account account = accountOptional.get();

            // Hash the new password
            String hashedPassword = passwordEncoder.encode(newPassword);
            logger.info("New hashed password for {}: {}", email, hashedPassword);

            account.setPassword(hashedPassword);
            accountRepository.save(account);

            logger.info("Password updated for: {}", email);
        }
        else
        {
            throw new IllegalArgumentException("Account not found with email: " + email);
        }
    }


}