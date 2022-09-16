package com.example.store.account.presentation;


import com.example.store.account.application.LoginService;
import com.example.store.account.dto.AccountLoginReq;
import com.example.store.account.dto.AccountLoginRes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountLoginController {

    private final LoginService loginService;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AccountLoginReq req,
                                   HttpServletRequest request) {
        AccountLoginRes account = loginService.login(req);
        HttpSession session = request.getSession();
        session.setAttribute("loginSession", account.getAccountId());
        return ResponseEntity.ok(account);
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long accountId = null;
        if (session != null) {
            accountId = (Long)session.getAttribute("loginSession");
            session.invalidate();
        }
        return ResponseEntity.ok("logout accountId : " + accountId );
    }
}
