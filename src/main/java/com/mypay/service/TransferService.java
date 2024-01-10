package com.mypay.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mypay.domain.Account;
import com.mypay.domain.Transfer;
import com.mypay.dto.TransferDTO;
import com.mypay.repository.TransferRepository;

@Service
public class TransferService {

    @Autowired
    private TransferRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mypay.transferauthservice.validate.url}")
    private String transferAuthServiceUrl;

    public void createTransfer(TransferDTO transferDto) throws Exception {
        Account fromAccount = accountService.findAccountById(transferDto.fromAccountId());
        Account toAccount = accountService.findAccountById(transferDto.toAccountId());

        accountService.validateTransfer(toAccount, transferDto.amount());

        if (!isTransferAuthorized(fromAccount, transferDto.amount())) {
            throw new Exception("Transação não autorizada pelo serviço.");
        }

        Transfer transfer = new Transfer(null, transferDto.amount(), fromAccount, toAccount);
        this.repository.save(transfer);

        accountService.updateAccountBalanceFromTransfer(fromAccount, toAccount, transfer.getAmount());
    }

    public Boolean isTransferAuthorized(Account fromAccount, BigDecimal amount) {
        ResponseEntity<Map> response = restTemplate.getForEntity(transferAuthServiceUrl, Map.class);

        HttpStatusCode statusCode = response.getStatusCode();
        String responseMessage = response.getBody().get("message").toString();

        if (statusCode.is2xxSuccessful() && responseMessage == "Autorizado") return true;
        return false;
    }
}
