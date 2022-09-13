package com.example.store.account.application;


import com.example.store.account.domain.Account;
import com.example.store.account.domain.AccountRepository;
import com.example.store.account.domain.Email;
import com.example.store.account.dto.AccountLoginReq;
import com.example.store.account.dto.AccountLoginRes;
import com.example.store.account.exception.AccountNotFoundException;
import com.example.store.account.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public AccountLoginRes login(AccountLoginReq accountLoginReq) {
        Account account = accountRepository.findByEmail(Email.of(accountLoginReq.getEmail()))
                .orElseThrow(() -> new AccountNotFoundException(Email.of(accountLoginReq.getEmail())));
        if (!account.checkPassword(accountLoginReq.getPassword())) {
            throw new WrongPasswordException();
        }
        return new AccountLoginRes(account.getId(),account.getEmail().getEmail());
    }

}
