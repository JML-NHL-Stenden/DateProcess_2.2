package com.nhlstenden.netflix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nhlstenden.netflix.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Account Management", description = "Account operations")
public class AccountController {
    @Autowired
    private AccountService accountService;

//    @PostMapping("/register")
//    @Operation(summary = "Register new account")
//    public ResponseEntity<AccountDTO> registerAccount(@RequestBody AccountRegistrationDTO dto) {
//        return ResponseEntity.ok(accountService.registerAccount(dto));
//    }

//    @PostMapping("/login")
//    @Operation(summary = "Login to account")
//    public ResponseEntity<JwtResponse> login(@RequestBody LoginDTO loginDto) {
//        return ResponseEntity.ok(accountService.login(loginDto));
//    }

//    @GetMapping("/{accountId}")
//    @Operation(summary = "Get account details")
//    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountId) {
//        return ResponseEntity.ok(accountService.getAccount(accountId));
//    }

//    @PatchMapping("/{accountId}")
//    @Operation(summary = "Update account details")
//    public ResponseEntity<AccountDTO> updateAccount(
//            @PathVariable Long accountId,
//            @RequestBody AccountUpdateDTO dto) {
//        return ResponseEntity.ok(accountService.updateAccount(accountId, dto));
//    }

    @DeleteMapping("/{accountId}")
    @Operation(summary = "Delete account")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{accountId}/block")
    @Operation(summary = "Block account")
    public ResponseEntity<Void> blockAccount(@PathVariable Long accountId) {
        accountService.blockAccount(accountId);
        return ResponseEntity.ok().build();
    }
}