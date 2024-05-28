package com.ramco.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramco.bank.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
