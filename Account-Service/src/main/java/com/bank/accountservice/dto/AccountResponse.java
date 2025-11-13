package com.bank.accountservice.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {
    private String accountNumber;
    private String customerId;
    private BigDecimal balance;
    private String accountType;
}
