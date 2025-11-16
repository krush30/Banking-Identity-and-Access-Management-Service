package com.bank.transactionservice.service;

import com.bank.transactionservice.dto.TransferRequest;
import com.bank.transactionservice.dto.TransactionResponse;
import com.bank.transactionservice.entity.Transaction;
import com.bank.transactionservice.exception.InsufficientBalanceException;
import com.bank.transactionservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final PaymentGateway paymentGateway;
    private final FraudDetectionService fraudDetectionService;

    @Transactional
    public TransactionResponse transferFunds(TransferRequest request) {
        if (fraudDetectionService.isSuspicious(request)) {
            return new TransactionResponse(null, "FAILED", "Suspicious transaction blocked");
        }

        boolean result = paymentGateway.processPayment(request);
        if (!result) throw new InsufficientBalanceException("Insufficient balance");

        Transaction transaction = Transaction.builder()
                .senderAccount(request.getSenderAccount())
                .receiverAccount(request.getReceiverAccount())
                .amount(request.getAmount())
                .mode(request.getMode())
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        return new TransactionResponse(
                UUID.randomUUID().toString(),
                "SUCCESS",
                "Transaction completed successfully"
        );
    }
}
