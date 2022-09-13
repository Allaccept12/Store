package com.example.store.account.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    protected Account(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public static Account createAccount(Email email, Password password) {
        return new Account(email, password);
    }

    public boolean checkPassword(String password) {
        return this.getPassword().isMatched(password);
    }

}
