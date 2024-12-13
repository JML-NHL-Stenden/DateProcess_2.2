package com.nhlstenden.netflix.service;

import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Account with email '" + email + "' does not exist in the database"));
    }

    public Account getAccountById(Integer accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID '" + accountId + "' does not exist in the database"));
    }

    public Account createAccount(Account account) {
        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Account already exists with email: " + account.getEmail());
        }
        return accountRepository.save(account);
    }

    public Account updateAccount(String email, Account account) {
        Account existingAccount = getAccountByEmail(email);
        account.setAccountId(existingAccount.getAccountId());
        return accountRepository.save(account);
    }

    public Account addOrUpdateAccount(String email, Account account) {
        Account existingAccount = accountRepository.findByEmail(email).orElse(null);
        if (existingAccount != null) {
            account.setAccountId(existingAccount.getAccountId());
        }
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
