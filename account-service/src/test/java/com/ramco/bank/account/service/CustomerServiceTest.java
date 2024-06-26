package com.ramco.bank.account.service;

import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ramco.bank.account.dto.CustomerDto;
import com.ramco.bank.account.dto.converter.CustomerDtoConverter;
import com.ramco.bank.account.exception.CustomerNotFoundException;
import com.ramco.bank.account.model.Customer;
import com.ramco.bank.account.repository.CustomerRepository;
import com.ramco.bank.account.service.CustomerService;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        converter = Mockito.mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository, converter);
    }

    @Test
    public void testFindCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Customer result = customerService.findCustomerById("id");
        Assert.assertEquals(result, customer);
    }

    @Test
    public void testFindCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        Assert.assertThrows(CustomerNotFoundException.class,
                () -> customerService.findCustomerById("id"));
    }

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id", "name", "surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.customerToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerById("id");
        Assert.assertEquals(result, customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        Assert.assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById("id"));
        Mockito.verifyNoInteractions(converter);
    }

}
