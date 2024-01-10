package com.mypay.dto;

public record NewAccountDTO(
    String firstName, 
    String lastName, 
    String email, 
    String password, 
    String cpfCnpj, 
    String accountType
) {}
