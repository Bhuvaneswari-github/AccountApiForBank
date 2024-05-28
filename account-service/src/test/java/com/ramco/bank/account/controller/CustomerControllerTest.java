package com.ramco.bank.account.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ramco.bank.account.dto.CustomerDto;
import com.ramco.bank.account.dto.converter.CustomerDtoConverter;
import com.ramco.bank.account.model.Customer;
import com.ramco.bank.account.repository.CustomerRepository;
import com.ramco.bank.account.service.AccountService;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class CustomerControllerTest extends TestSupport {
	
	
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public AccountService accountService;

    @Autowired
    public CustomerDtoConverter converter;

    public final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }


    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomerDto() throws Exception {

        Customer customer = customerRepository.save(generateCustomer());
        accountService.createAccount(generateCreateAccountRequest(customer.getId(), 100));

        CustomerDto expected = converter.customerToCustomerDto(
                customerRepository.getById(
                        Objects.requireNonNull(customer.getId())));

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "non-exists-customer"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
