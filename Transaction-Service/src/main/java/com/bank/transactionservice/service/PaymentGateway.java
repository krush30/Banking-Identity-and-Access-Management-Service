package com.bank.transactionservice.service;

import com.bank.transactionservice.dto.TransferRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentGateway {
    public boolean processPayment(TransferRequest request) {
        // In real system, call Account Service to verify and deduct balance
        return request.getAmount() <= 50000; // mock rule
    }
}
