package com.bank.transactionservice.service;

import com.bank.transactionservice.dto.TransferRequest;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionService {
    public boolean isSuspicious(TransferRequest request) {
        // Very basic mock rule for demo
        return request.getAmount() > 100000;
    }
}
