package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.dto.AccountDTO;
import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.exception.ResourceNotFoundException;
import com.nhlstenden.netflix.service.AccountService;
import com.nhlstenden.netflix.config.JwtUtil;
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
public class AccountController {

    private final AccountService accountService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AccountController(AccountService accountService, JwtUtil jwtUtil) {
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    // Fetch all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(accounts);
    }

    // Fetch account by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getAccountByEmail(@PathVariable String email) {
        try {
            Account account = accountService.getAccountByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found with email: " + email));
            return ResponseEntity.ok(account);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Fetch account by ID
    @GetMapping("/id/{accountId}")
    public ResponseEntity<Object> getAccountById(@PathVariable Integer accountId) {
        try {
            Account account = accountService.getAccountById(accountId)
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));
            return ResponseEntity.ok(account);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Create a new account
    @PostMapping
    public ResponseEntity<Object> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        try {
            Account account = new Account();
            account.setEmail(accountDTO.getEmail());
            account.setPassword(accountDTO.getPassword()); // Assume hashing in service layer
            account.setRole(accountDTO.getRole() != null ? accountDTO.getRole() : "USER");
            Account createdAccount = accountService.createAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Update an existing account
    @PutMapping("/{email}")
    public ResponseEntity<Object> updateAccount(@PathVariable String email, @Valid @RequestBody AccountDTO accountDTO) {
        try {
            Account account = new Account();
            account.setEmail(accountDTO.getEmail());
            account.setPassword(accountDTO.getPassword());
            account.setRole(accountDTO.getRole());
            Account updatedAccount = accountService.updateAccount(email, account);
            return ResponseEntity.ok(updatedAccount);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Delete an account
    @DeleteMapping("/{email}")
    public ResponseEntity<Object> deleteAccount(@PathVariable String email) {
        try {
            accountService.deleteAccount(email);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Block an account
    @PatchMapping("/{email}/block")
    public ResponseEntity<Object> blockAccount(@PathVariable String email) {
        try {
            accountService.blockAccount(email);
            return ResponseEntity.ok(Map.of("message", "Account blocked successfully"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Unblock an account
    @PatchMapping("/{email}/unblock")
    public ResponseEntity<Object> unblockAccount(@PathVariable String email) {
        try {
            accountService.unblockAccount(email);
            return ResponseEntity.ok(Map.of("message", "Account unblocked successfully"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Change password for the currently authenticated user
    @PutMapping("/self/password")
    public ResponseEntity<Object> changePassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> payload) {
        try {
            String email = jwtUtil.extractEmail(token.substring(7)); // Extract email from token
            String newPassword = payload.get("password");
            accountService.changePassword(email, newPassword);
            return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to change password: " + e.getMessage()));
        }
    }

    // Delete the currently authenticated user's account
    @DeleteMapping("/self")
    public ResponseEntity<Object> deleteOwnAccount(@RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtil.extractEmail(token.substring(7)); // Extract email from token
            accountService.deleteAccount(email);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to delete account: " + e.getMessage()));
        }
    }
}
