package com.RideApp.dto;

import java.time.LocalDateTime;

import com.RideApp.enums.TransactionMethod;
import com.RideApp.enums.TransactionType;

import lombok.Data;

@Data
public class WalletTransactionDto {


    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

 
    private RideDto ride;

    private String transactionId;

    private WalletDto wallet;

    
    private LocalDateTime timeStamp;	
}
