package com.ramco.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String id;

    private BigDecimal balance = BigDecimal.ZERO;

    private LocalDateTime creationDate;

    private AccountCustomerDto customer;

    private Set<TransactionDto> transactions;

}
