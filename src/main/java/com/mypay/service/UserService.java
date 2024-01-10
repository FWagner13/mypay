package com.mypay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypay.domain.Account;
import com.mypay.domain.User;
import com.mypay.dto.NewAccountDTO;
import com.mypay.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
}
