package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController
{

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts()
    {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Account> getAccount(@PathVariable String email)
    {
        return ResponseEntity.ok(accountService.getAccountByEmail(email));
    }

    @GetMapping("/id/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer accountId)
    {
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account)
    {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Account> addOrUpdateAccount(@PathVariable String email, @RequestBody Account account)
    {
        Account result = accountService.addOrUpdateAccount(email, account);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String email)
    {
        accountService.deleteAccount(email);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{email}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable String email)
    {
        accountService.blockAccount(email);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{email}/unblock")
    public ResponseEntity<Void> unblockAccount(@PathVariable String email)
    {
        accountService.unblockAccount(email);
        return ResponseEntity.ok().build();
    }
}
