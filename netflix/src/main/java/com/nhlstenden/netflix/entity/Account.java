package com.nhlstenden.netflix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "password_method")
    private String passwordMethod;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "restore_token")
    private Integer restoreToken;

    @Column(name = "init_duration_end")
    private java.sql.Timestamp initDurationEnd;

    @Column(name = "payment_account_string")
    private String paymentAccountString;

    // Constructors
    public Account()
    {
    }

    public Account(String email, String password, String passwordMethod, Boolean isBlocked,
            Integer restoreToken, java.sql.Timestamp initDurationEnd, String paymentAccountString)
    {
        this.email = email;
        this.password = password;
        this.passwordMethod = passwordMethod;
        this.isBlocked = isBlocked;
        this.restoreToken = restoreToken;
        this.initDurationEnd = initDurationEnd;
        this.paymentAccountString = paymentAccountString;
    }

    // Getters and Setters
    public Integer getAccountId()
    {
        return accountId;
    }

    public void setAccountId(Integer accountId)
    {
        this.accountId = accountId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordMethod()
    {
        return passwordMethod;
    }

    public void setPasswordMethod(String passwordMethod)
    {
        this.passwordMethod = passwordMethod;
    }

    public Boolean getIsBlocked()
    {
        return isBlocked;
    }

    public void setIsBlocked(Boolean blocked)
    {
        this.isBlocked = blocked;
    }

    public Integer getRestoreToken()
    {
        return restoreToken;
    }

    public void setRestoreToken(Integer restoreToken)
    {
        this.restoreToken = restoreToken;
    }

    public java.sql.Timestamp getInitDurationEnd()
    {
        return initDurationEnd;
    }

    public void setInitDurationEnd(java.sql.Timestamp initDurationEnd)
    {
        this.initDurationEnd = initDurationEnd;
    }

    public String getPaymentAccountString()
    {
        return paymentAccountString;
    }

    public void setPaymentAccountString(String paymentAccountString)
    {
        this.paymentAccountString = paymentAccountString;
    }
}