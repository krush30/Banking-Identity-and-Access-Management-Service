package com.bank.transactionservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {
    @NotNull
    private String senderAccount;

    @NotNull
    private String receiverAccount;

    @NotNull
    private Double amount;

    @NotNull
    private String mode; // IMPS, NEFT, RTGS
}
