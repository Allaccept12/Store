package com.example.store.account.application;

import com.example.store.account.domain.Account;
import com.example.store.account.domain.Email;

public interface AccountService {

    Account getAccount(Long accountId);
    Account getAccount(Email accountEmail);
}
