package com.bank.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private String transactionId;
    private String status;
    private String message;
}
