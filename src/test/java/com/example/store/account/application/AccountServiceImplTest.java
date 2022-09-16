package com.example.store.account.application;

import com.example.store.account.domain.Account;
import com.example.store.account.domain.AccountRepository;
import com.example.store.account.domain.Email;
import com.example.store.account.domain.Password;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@MockitoSettings(strictness = Strictness.WARN)
class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Test
    void getAccountById() {
        //given
        given(accountRepository.findById(anyLong())).willReturn(Optional.of(Account.createAccount(Email.of("testEmail"), Password.of("123123"))));
        //when
        Account account = accountService.getAccount(1L);
        // then
        Assertions.assertNotNull(account);

    }

    @Test
    void getAccountByEmail() {

        //given
        given(accountRepository.findByEmail(any())).willReturn(Optional.of(Account.createAccount(Email.of("testEmail"), Password.of("123123"))));
        //when
        Account account = accountService.getAccount("testEmail");
        // then
        Assertions.assertNotNull(account);

    }

}