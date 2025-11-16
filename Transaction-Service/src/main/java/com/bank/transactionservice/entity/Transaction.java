package com.bank.transactionservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderAccount;
    private String receiverAccount;
    private Double amount;
    private String mode; // IMPS, NEFT, RTGS
    private String status; // SUCCESS, FAILED
    private LocalDateTime timestamp;
}
