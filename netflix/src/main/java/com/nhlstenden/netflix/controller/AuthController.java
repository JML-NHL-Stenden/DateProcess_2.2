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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AccountService accountService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AccountService accountService, JwtUtil jwtUtil) {
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody AccountDTO accountDTO) {
        logger.info("Register request received for email: {}", accountDTO.getEmail());
        try {
            Account account = new Account();
            account.setEmail(accountDTO.getEmail());
            account.setPassword(accountDTO.getPassword());
            account.setRole(accountDTO.getRole());
            accountService.createAccount(account);

            logger.info("Registration successful for email: {}", accountDTO.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Registration successful");
            response.put("email", accountDTO.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Registration failed for email: {}", accountDTO.getEmail(), e);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Registration failed");
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AccountDTO accountDTO) {
        logger.info("Login request received for email: {}", accountDTO.getEmail());
        try {
            boolean isAuthenticated = accountService.authenticate(accountDTO.getEmail(), accountDTO.getPassword());

            if (isAuthenticated) {
                String token = jwtUtil.generateToken(accountDTO.getEmail());
                logger.info("Generated token for email {}: {}", accountDTO.getEmail(), token);

                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                logger.warn("Invalid credentials for email: {}", accountDTO.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
            }
        } catch (ResourceNotFoundException e) {
            logger.error("Login failed for email: {}, resource not found", accountDTO.getEmail(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            logger.error("Login failed for email: {}", accountDTO.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Login failed: " + e.getMessage()));
        }
    }
}
