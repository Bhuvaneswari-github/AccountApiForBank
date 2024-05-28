package com.ramco.bank.account.dto.converter;

import com.ramco.bank.account.dto.TransactionDto;
import com.ramco.bank.account.model.Transaction;

import org.springframework.stereotype.Component;


@Component
public class TransactionDtoConverter {

    public TransactionDto transactionToTransactionDto(Transaction transaction) {

        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTransactionDate()
        );

    }

}
