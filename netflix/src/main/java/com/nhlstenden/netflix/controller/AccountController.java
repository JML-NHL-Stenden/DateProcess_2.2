package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // Get account by email
    @GetMapping("/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email) {
        return ResponseEntity.ok(accountService.getAccountByEmail(email));
    }

    // Get account by ID
    @GetMapping("/id/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer accountId) {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    // Create a new account
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    // Update an existing account
    @PutMapping("/{email}")
    public ResponseEntity<Account> updateAccount(@PathVariable String email, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(email, account));
    }

    // Delete an account by email
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String email) {
        accountService.deleteAccount(email);
        return ResponseEntity.noContent().build();
    }

    // Block an account
    @PatchMapping("/{email}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable String email) {
        accountService.blockAccount(email);
        return ResponseEntity.ok().build();
    }

    // Unblock an account
    @PatchMapping("/{email}/unblock")
    public ResponseEntity<Void> unblockAccount(@PathVariable String email) {
        accountService.unblockAccount(email);
        return ResponseEntity.ok().build();
    }
}
