package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.dto.AccountDTO;
import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.service.AccountService;
import com.nhlstenden.netflix.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController
{

    private final AccountService accountService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AccountController(AccountService accountService, JwtUtil jwtUtil)
    {
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    // Fetch all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts()
    {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // Fetch account by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email)
    {
        Optional<Account> account = accountService.getAccountByEmail(email);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Fetch account by ID
    @GetMapping("/id/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer accountId)
    {
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new account
    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDTO accountDTO)
    {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword()); // Assume hashing is handled in the service layer
        account.setRole(accountDTO.getRole() != null ? accountDTO.getRole() : "USER");
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
    }

    // Update an existing account
    @PutMapping("/{email}")
    public ResponseEntity<Account> updateAccount(@PathVariable String email, @Valid @RequestBody AccountDTO accountDTO)
    {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword()); // Do not hash here
        account.setRole(accountDTO.getRole());
        return ResponseEntity.ok(accountService.updateAccount(email, account));
    }

    // Delete an account
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String email)
    {
        accountService.deleteAccount(email);
        return ResponseEntity.noContent().build();
    }

    // Block an account
    @PatchMapping("/{email}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable String email)
    {
        accountService.blockAccount(email);
        return ResponseEntity.ok().build();
    }

    // Unblock an account
    @PatchMapping("/{email}/unblock")
    public ResponseEntity<Void> unblockAccount(@PathVariable String email)
    {
        accountService.unblockAccount(email);
        return ResponseEntity.ok().build();
    }

    // Change password for the currently authenticated user
    @PutMapping("/self/password")
    public ResponseEntity<Void> changePassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> payload)
    {
        try
        {
            String email = jwtUtil.extractEmail(token.substring(7)); // Extract email from token
            String newPassword = payload.get("password");
            accountService.changePassword(email, newPassword); // Change password in the service
            return ResponseEntity.ok().build();
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete the currently authenticated user's account
    @DeleteMapping("/self")
    public ResponseEntity<Void> deleteOwnAccount(@RequestHeader("Authorization") String token)
    {
        try
        {
            String email = jwtUtil.extractEmail(token.substring(7)); // Extract email from token
            accountService.deleteAccount(email); // Delete account in the service
            return ResponseEntity.noContent().build();
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
