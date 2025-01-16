package com.nhlstenden.netflix.config;

import com.nhlstenden.netflix.config.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        // Allow access to public files (index, login, register)
                        .requestMatchers("/index.html", "/login.html", "/register.html", "/home.html", "/static/**").permitAll()
                        .requestMatchers("/auth/**").permitAll() // Allow authentication routes
                        // Restrict other routes
                        .requestMatchers("/movies/finance/**").hasRole("SENIOR")
                        .requestMatchers("/movies/**").hasAnyRole("SENIOR", "MEDIOR", "JUNIOR")
                        .requestMatchers("/api/**").hasRole("API")
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return http.build();
    }
}
