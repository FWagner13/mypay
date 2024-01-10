package com.mypay.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypay.account.AccountType;
import com.mypay.domain.Account;
import com.mypay.domain.User;
import com.mypay.dto.NewAccountDTO;
import com.mypay.exception.BusinessException;
import com.mypay.exception.ResourceNotFoundException;
import com.mypay.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public void validateTransfer(Account sender, BigDecimal amount) throws BusinessException {
        if (sender.getType().isOutlet()) {
            throw new BusinessException("Não é possível realizar transferências como lojista.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Saldo insuficiente.");
        }
    }

    public Account findAccountById(Long id) throws ResourceNotFoundException {
        return this.repository.findAccountById(id).orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada."));
    }

    public Account createAccount(NewAccountDTO accountDTO) {
        User user = new User(accountDTO);
        Account account = new Account(AccountType.valueOf(accountDTO.accountType()), user);
        this.repository.save(account);
        return account;
    }

    public void updateAccountBalanceFromTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        BigDecimal fromAccountNewBalance = fromAccount.getBalance().subtract(amount);
        fromAccount.setBalance(fromAccountNewBalance);
        
        BigDecimal toAccountNewBalance = toAccount.getBalance().add(amount);
        toAccount.setBalance(toAccountNewBalance);

        repository.save(fromAccount);
        repository.save(toAccount);
    }

    public List<Account> findAll() {
        return this.repository.findAll();
    }
}
