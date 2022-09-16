package com.example.store.account.presentation;

import com.example.store.account.application.LoginService;
import com.example.store.account.dto.AccountLoginReq;
import com.example.store.account.dto.AccountLoginRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountLoginController.class)
class AccountLoginControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    LoginService loginService;

    @Test
    void login() throws Exception {
        AccountLoginReq req = new AccountLoginReq("email", "123123");
        AccountLoginRes res = new AccountLoginRes(1L, "123123");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(req);
        given(loginService.login(any())).willReturn(res);

        mvc.perform(post("/account/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(loginService).login( ArgumentMatchers.refEq(req));
    }
}