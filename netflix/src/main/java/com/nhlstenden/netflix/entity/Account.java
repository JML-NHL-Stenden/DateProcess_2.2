package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "account")
public class Account {

    public static final String ROLE_JUNIOR = "JUNIOR";
    public static final String ROLE_MEDIOR = "MEDIOR";
    public static final String ROLE_SENIOR = "SENIOR";
    public static final String ROLE_API = "API";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "payment_method", length = 255)
    private String paymentMethod;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "restore_token")
    private Integer restoreToken;

    @Column(name = "account_created_date", nullable = false)
    private Timestamp accountCreatedDate;

    @Column(name = "init_duration_end")
    private Timestamp initDurationEnd;

    @Column(name = "password_method", length = 255)
    private String passwordMethod;

    @Column(name = "payment_account_string", length = 255)
    private String paymentAccountString;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    // Default constructor
    public Account() {}

    // Parameterized constructor
    public Account(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Integer getRestoreToken() {
        return restoreToken;
    }

    public void setRestoreToken(Integer restoreToken) {
        this.restoreToken = restoreToken;
    }

    public Timestamp getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(Timestamp accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

    public Timestamp getInitDurationEnd() {
        return initDurationEnd;
    }

    public void setInitDurationEnd(Timestamp initDurationEnd) {
        this.initDurationEnd = initDurationEnd;
    }

    public String getPasswordMethod() {
        return passwordMethod;
    }

    public void setPasswordMethod(String passwordMethod) {
        this.passwordMethod = passwordMethod;
    }

    public String getPaymentAccountString() {
        return paymentAccountString;
    }

    public void setPaymentAccountString(String paymentAccountString) {
        this.paymentAccountString = paymentAccountString;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
