package com.example.store.account.presentation;


import com.example.store.account.application.RegisterService;
import com.example.store.account.dto.AccountRegisterReq;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountRegisterController {

    private final RegisterService registerService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/account/register")
    public ResponseEntity<?> register(@RequestBody @Valid AccountRegisterReq req) {
        return ResponseEntity.ok(registerService.register(req));
    }
}
