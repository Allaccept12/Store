package com.example.store.account.dto;


import lombok.Getter;

@Getter
public class AccountLoginRes {

    private Long accountId;

    private String accountEmail;

    public AccountLoginRes() {
    }

    public AccountLoginRes(Long accountId, String accountEmail) {
        this.accountId = accountId;
        this.accountEmail = accountEmail;
    }
}
