package com.bank.accountservice.service;

import com.bank.accountservice.dto.StatementResponse;
import com.bank.accountservice.entity.Account;
import com.bank.accountservice.exception.AccountNotFoundException;
import com.bank.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatementService {

    private final AccountRepository accountRepository;

    public StatementResponse generateStatement(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        // No PDF generation – just return a JSON statement
        return new StatementResponse(
                account.getAccountNumber(),
                account.getBalance(),
                "Statement generated successfully"
        );
    }
}
