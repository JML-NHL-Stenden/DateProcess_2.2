package com.nhlstenden.Netflix.controller;

import com.nhlstenden.Netflix.entity.Account;
import com.nhlstenden.Netflix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final EntityManager entityManager;

    @Autowired
    public AccountController(AccountService accountService, EntityManager entityManager) {
        this.accountService = accountService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Account> getAccount(@PathVariable String email) {
        return ResponseEntity.ok(accountService.getAccountByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Account> updateAccount(@PathVariable String email, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(email, account));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String email) {
        accountService.deleteAccount(email);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{email}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable String email) {
        accountService.blockAccount(email);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{email}/unblock")
    public ResponseEntity<Void> unblockAccount(@PathVariable String email) {
        accountService.unblockAccount(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccountsDirect() {
        Query query = entityManager.createNativeQuery("SELECT * FROM account", Account.class);
        @SuppressWarnings("unchecked")
        List<Account> accounts = (List<Account>) query.getResultList();
        return ResponseEntity.ok(accounts);
    }
}