package com.bank.accountservice.controller;

import com.bank.accountservice.dto.AccountRequest;
import com.bank.accountservice.dto.AccountResponse;
import com.bank.accountservice.dto.StatementResponse;
import com.bank.accountservice.service.AccountService;
import com.bank.accountservice.service.StatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final StatementService statementService; // ✅ Added this

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountResponse>> getCustomerAccounts(@PathVariable String customerId) {
        return ResponseEntity.ok(accountService.getAccountsByCustomer(customerId));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok("Account deleted successfully");
    }

   @PatchMapping("/{accountNumber}/deposit")
public ResponseEntity<AccountResponse> depositMoney(
        @PathVariable String accountNumber,
        @RequestParam double amount) {
    return ResponseEntity.ok(accountService.depositMoney(accountNumber, amount));
}

@GetMapping("/{accountNumber}/statements")
public ResponseEntity<StatementResponse> getStatements(@PathVariable String accountNumber) {
    return ResponseEntity.ok(statementService.generateStatement(accountNumber));
}


}
