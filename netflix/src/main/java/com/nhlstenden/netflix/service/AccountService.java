package com.nhlstenden.Netflix.service;

import com.nhlstenden.Netflix.entity.Account;
import com.nhlstenden.Netflix.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new EntityNotFoundException("Account not found with email: " + email);
        }
        return account;
    }

    public Account createAccount(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("Account already exists with email: " + account.getEmail());
        }
        return accountRepository.save(account);
    }

    public Account updateAccount(String email, Account account) {
        Account existingAccount = getAccountByEmail(email);
        account.setAccountId(existingAccount.getAccountId());
        return accountRepository.save(account);
    }

    public void deleteAccount(String email) {
        Account account = getAccountByEmail(email);
        accountRepository.deleteById(account.getAccountId());
    }

    public void blockAccount(String email) {
        Account account = getAccountByEmail(email);
        account.setIsBlocked(true);
        accountRepository.save(account);
    }

    public void unblockAccount(String email) {
        Account account = getAccountByEmail(email);
        account.setIsBlocked(false);
        accountRepository.save(account);
    }
}