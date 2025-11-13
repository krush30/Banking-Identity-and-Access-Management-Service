package com.bank.accountservice.service;

import com.bank.accountservice.dto.AccountRequest;
import com.bank.accountservice.dto.AccountResponse;
import com.bank.accountservice.entity.Account;
import com.bank.accountservice.exception.AccountNotFoundException;
import com.bank.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponse createAccount(AccountRequest request) {
        Account account = Account.builder()
                .accountNumber(UUID.randomUUID().toString())
                .customerId(request.getCustomerId())
                .accountType(request.getAccountType())
                .balance(request.getInitialDeposit() != null ? request.getInitialDeposit() : BigDecimal.ZERO)
                .build();

        accountRepository.save(account);

        return mapToResponse(account);
    }

    public List<AccountResponse> getAccountsByCustomer(String customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public AccountResponse getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        return mapToResponse(account);
    }

    public void deleteAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        accountRepository.delete(account);
    }

    private AccountResponse mapToResponse(Account account) {
        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .balance(account.getBalance())
                .accountType(account.getAccountType())
                .build();
    }

   public AccountResponse depositMoney(String accountNumber, double amount) {
    Account account = accountRepository.findByAccountNumber(accountNumber)
            .orElseThrow(() -> new AccountNotFoundException("Account not found"));

    // Use BigDecimal arithmetic instead of '+'
    account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));

    accountRepository.save(account);
    return mapToResponse(account);
}


    

}
