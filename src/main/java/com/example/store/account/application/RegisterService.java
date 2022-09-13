package com.example.store.account.application;

import com.example.store.account.dto.AccountRegisterReq;
import com.example.store.account.domain.Account;
import com.example.store.account.domain.AccountRepository;
import com.example.store.account.domain.Email;
import com.example.store.account.domain.Password;
import com.example.store.account.exception.EmailDuplicationException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long register(AccountRegisterReq accountRegisterRequest) {
        if (checkDuplicatedEmail(accountRegisterRequest.getEmail())) {
            throw new EmailDuplicationException(Email.of(accountRegisterRequest.getEmail()));
        }
        Account account
                = Account.createAccount(Email.of(accountRegisterRequest.getEmail()), Password.of(accountRegisterRequest.getPassword()));
        accountRepository.save(account);
        return account.getId();
    }

    private boolean checkDuplicatedEmail(String email) {
        return accountRepository.existByEmail(Email.of(email)) != null;

    }

}
