package com.mypay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mypay.domain.Account;
import com.mypay.domain.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long>{
    public Optional<Transfer> findTransferById(Long id);
    public Optional<Transfer> findTransferByFromAccount(Account fromAccount);
    public Optional<Transfer> findTransferByToAccount(Account toAccount);
    public Optional<Transfer> findTransferByFromAccountAndToAccount(Account fromAccount, Account toAccount);
}
