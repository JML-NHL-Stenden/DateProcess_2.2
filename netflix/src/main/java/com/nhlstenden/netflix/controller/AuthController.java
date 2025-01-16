package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.dto.AccountDTO;
import com.nhlstenden.netflix.entity.Account;
import com.nhlstenden.netflix.mapper.AccountMapper;
import com.nhlstenden.netflix.service.AccountService;
import com.nhlstenden.netflix.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
            // Convert AccountDTO to Account
            Account account = AccountMapper.toEntity(accountDTO);

            // Call the service with the mapped entity
            accountService.createAccount(account);

            logger.info("Registration successful for email: {}", accountDTO.getEmail());

            // Response for testing purposes
            Map<String, String> response = new HashMap<>();
            response.put("message", "Registration successful");
            response.put("email", accountDTO.getEmail());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Registration failed for email: {}", accountDTO.getEmail(), e);

            // Response for testing purposes
            Map<String, String> response = new HashMap<>();
            response.put("message", "Registration failed");
            response.put("error", e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AccountDTO accountDTO) {
        logger.info("Login request received for email: {}", accountDTO.getEmail());
        try {
            boolean isAuthenticated = accountService.authenticate(accountDTO.getEmail(), accountDTO.getPassword());
            if (isAuthenticated) {
                // Generate token
                String token = jwtUtil.generateToken(accountDTO.getEmail());

                // Log the generated token
                logger.info("Generated token for email {}: {}", accountDTO.getEmail(), token);

                // Extract and log token details
                String usernameFromToken = jwtUtil.extractUsername(token);
                boolean isTokenValid = jwtUtil.validateToken(token, accountDTO.getEmail());
                long tokenExpiration = jwtUtil.extractExpiration(token).getTime();

                logger.info("Token details: [username: {}, valid: {}, expiration: {}]",
                        usernameFromToken, isTokenValid, tokenExpiration);

                // Prepare response
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("token_valid", String.valueOf(isTokenValid));
                response.put("token_expiration", String.valueOf(tokenExpiration));

                return ResponseEntity.ok(response);
            } else {
                logger.warn("Login failed for email: {}", accountDTO.getEmail());
                return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
            }
        } catch (Exception e) {
            logger.error("Login failed for email: {}", accountDTO.getEmail(), e);
            return ResponseEntity.status(500).body(Map.of("error", "Login failed: " + e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestHeader("Authorization") String token) {
        logger.info("Logout request received with token: {}", token);
        try {
            // Check token validity (if needed)
            boolean isValid = jwtUtil.validateToken(token.substring(7), jwtUtil.extractUsername(token.substring(7)));

            if (isValid) {
                logger.info("Token is valid during logout: {}", token);

                // Invalidate the token (if implemented, such as in a blacklist mechanism)
                logger.info("Logout successful for token: {}", token);

                return ResponseEntity.ok(Map.of("message", "Logout successful"));
            } else {
                logger.warn("Invalid token provided during logout: {}", token);
                return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
            }
        } catch (Exception e) {
            logger.error("Logout failed with token: {}", token, e);
            return ResponseEntity.status(500).body(Map.of("error", "Logout failed: " + e.getMessage()));
        }
    }
}
