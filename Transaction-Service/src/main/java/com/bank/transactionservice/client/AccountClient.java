// package com.bank.transactionservice.client;

// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @FeignClient(name = "account-service", url = "http://localhost:8082/api/accounts")
// public interface AccountClient {

//     @GetMapping("/{accountNumber}")
//     Map<String, Object> getAccount(@PathVariable("accountNumber") String accountNumber);

//     @PutMapping("/{accountNumber}/debit")
//     void debit(@PathVariable("accountNumber") String accountNumber, @RequestParam("amount") Double amount);

//     @PutMapping("/{accountNumber}/credit")
//     void credit(@PathVariable("accountNumber") String accountNumber, @RequestParam("amount") Double amount);
// }
