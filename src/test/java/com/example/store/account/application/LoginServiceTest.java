package com.example.store.account.application;

import com.example.store.account.domain.Account;
import com.example.store.account.domain.Email;
import com.example.store.account.domain.Password;
import com.example.store.account.dto.AccountLoginReq;
import com.example.store.account.dto.AccountLoginRes;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@MockitoSettings(strictness = Strictness.WARN)
class LoginServiceTest {

    @InjectMocks
    LoginService loginService;

    @Mock
    AccountService accountService;

    @Test
    void login() {
        //given
        AccountLoginReq loginReqDto = new AccountLoginReq("email", "123123");
        given(accountService.getAccount(anyString())).willReturn(Account.createAccount(Email.of("email"), Password.of("123123")));
        //when -
        AccountLoginRes result = loginService.login(loginReqDto);
        // then
        assertEquals(result.getAccountEmail(), "email");

    }
}