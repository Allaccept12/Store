package com.example.store.account.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AccountLoginReq {

    @NotBlank(message = "이메일은 필수 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;

    public AccountLoginReq() {
    }

    public AccountLoginReq(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
