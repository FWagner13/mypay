package com.mypay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypay.account.AccountType;
import com.mypay.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    public Optional<Account> findAccountById(Long id);
    public Optional<Account> findAccountByType(AccountType type);
}
