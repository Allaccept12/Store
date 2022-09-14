package com.example.store.account.application;


import com.example.store.account.domain.Account;
import com.example.store.account.domain.AccountRepository;

import com.example.store.account.domain.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Account getAccount(Email accountEmail) {
        return accountRepository.findByEmail(accountEmail)
                .orElseThrow(AccountNotFoundException::new);
    }
}
