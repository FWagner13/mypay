package com.mypay.dto;

import java.math.BigDecimal;

public record TransferDTO(Long fromAccountId, Long toAccountId, BigDecimal amount) {

}
