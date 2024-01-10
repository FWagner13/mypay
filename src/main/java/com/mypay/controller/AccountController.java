package com.mypay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypay.domain.Account;
import com.mypay.dto.NewAccountDTO;
import com.mypay.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/save")
    public ResponseEntity<Account> save(@RequestBody NewAccountDTO newAccountDTO) {
        Account account = accountService.createAccount(newAccountDTO);

        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accountList = this.accountService.findAll();

        return new ResponseEntity<List<Account>>(accountList, HttpStatus.CREATED);
    }
}
