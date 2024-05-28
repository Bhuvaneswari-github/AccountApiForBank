package com.ramco.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramco.bank.account.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, String> {
}
