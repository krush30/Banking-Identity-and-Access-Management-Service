package com.bank.transactionservice.controller;

import com.bank.transactionservice.dto.TransferRequest;
import com.bank.transactionservice.dto.TransactionResponse;
import com.bank.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public TransactionResponse transferFunds(@RequestBody TransferRequest request) {
        return transactionService.transferFunds(request);
    }

//     @PutMapping("/{accountNumber}/debit")
// public ResponseEntity<String> debitAccount(@PathVariable String accountNumber, @RequestParam Double amount) {
//     accountService.debit(accountNumber, amount);
//     return ResponseEntity.ok("Amount debited successfully");
// }

// @PutMapping("/{accountNumber}/credit")
// public ResponseEntity<String> creditAccount(@PathVariable String accountNumber, @RequestParam Double amount) {
//     accountService.credit(accountNumber, amount);
//     return ResponseEntity.ok("Amount credited successfully");
// }

}
