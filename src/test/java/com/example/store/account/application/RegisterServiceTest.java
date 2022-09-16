package com.example.store.account.application;

import com.example.store.account.domain.AccountRepository;
import com.example.store.account.dto.AccountRegisterReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class RegisterServiceTest {

    @Autowired
    RegisterService registerService;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setting() {
        accountRepository.deleteAll();
    }
    @Test
    void register() {
        //given
        AccountRegisterReq accountRegisterReq = new AccountRegisterReq("email", "123123");
        //when -
        Long result = registerService.register(accountRegisterReq);
        // then
        assertEquals(result, 1L);

    }

}