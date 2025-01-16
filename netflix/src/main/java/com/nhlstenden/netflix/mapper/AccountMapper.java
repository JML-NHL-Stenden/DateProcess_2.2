package com.nhlstenden.netflix.mapper;

import com.nhlstenden.netflix.dto.AccountDTO;
import com.nhlstenden.netflix.entity.Account;

public class AccountMapper {

    public static Account toEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setRole(accountDTO.getRole());
        return account;
    }
}
