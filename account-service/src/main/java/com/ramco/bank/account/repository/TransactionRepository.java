package com.ramco.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramco.bank.account.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
