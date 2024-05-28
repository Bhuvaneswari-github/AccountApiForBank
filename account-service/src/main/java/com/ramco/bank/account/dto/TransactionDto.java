package com.ramco.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ramco.bank.account.constant.TransactionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private String id;

    private BigDecimal amount;

    private TransactionType transactionType;
    //TransactionType transactionType = TransactionType.INITIAL;

    private LocalDateTime transactionDate;

    public TransactionDto(String id, TransactionType initial, BigDecimal bigDecimal, LocalDateTime localDateTime) {
    }
}
