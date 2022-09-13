package com.example.store.account.domain;


import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@Embeddable
public class Password {


    @Column(name = "password")
    private String value;

    protected Password() {
    }

    protected Password(String value) {
        this.value = encodePassword(value);
    }

    public static Password of(String password) {
        return new Password(password);
    }

    public boolean isMatched(String password) {
        return new BCryptPasswordEncoder().matches(password,this.value);
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
