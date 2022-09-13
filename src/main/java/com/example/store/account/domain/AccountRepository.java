package com.example.store.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a.id from Account a where a.email =:email")
    Long existByEmail(@Param("email") Email email);

    Optional<Account> findByEmail(Email email);
}
