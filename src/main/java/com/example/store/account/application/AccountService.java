package com.example.store.account.application;

import com.example.store.account.domain.Account;

public interface AccountService {

    Account getAccount(Long accountId);
    Account getAccount(String accountEmail);
}
