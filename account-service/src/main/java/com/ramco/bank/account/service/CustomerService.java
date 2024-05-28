package com.ramco.bank.account.service;

import com.ramco.bank.account.dto.CustomerDto;
import com.ramco.bank.account.dto.converter.CustomerDtoConverter;
import com.ramco.bank.account.exception.CustomerNotFoundException;
import com.ramco.bank.account.model.Customer;
import com.ramco.bank.account.repository.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(()
                -> new CustomerNotFoundException("Customer could not find by id: " + id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return customerDtoConverter.customerToCustomerDto(findCustomerById(customerId));
    }
}
