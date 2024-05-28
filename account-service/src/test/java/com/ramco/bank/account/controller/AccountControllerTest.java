package com.ramco.bank.account.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ramco.bank.account.dto.converter.CustomerDtoConverter;
import com.ramco.bank.account.dto.request.CreateAccountRequest;
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
public class AccountControllerTest extends TestSupport{
	
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
    public void testCreateAccount_whenCustomerIdExits_shouldCreateAccountAndReturnAccountDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        CreateAccountRequest request = generateCreateAccountRequest(customer.getId(), 100);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.balance", is(100)))
                .andExpect(jsonPath("$.customer.id", is(customer.getId())))
                .andExpect(jsonPath("$.customer.name", is(customer.getName())))
                .andExpect(jsonPath("$.customer.surname", is(customer.getSurname())))
                .andExpect(jsonPath("$.transactions", hasSize(1)));
    }

    @Test
    public void testCreateAccount_whenCustomerIdDoesNotExit_shouldReturn404NotFound() throws Exception {
        CreateAccountRequest request = generateCreateAccountRequest("id", 100);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateAccount_whenRequestHasNoCustomerId_shouldReturn400BadRequest() throws Exception {
        CreateAccountRequest request = generateCreateAccountRequest("", 100);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateAccount_whenRequestHasLessThanZeroInitialCreditValue_shouldReturn400BadRequest() throws Exception {
        CreateAccountRequest request = generateCreateAccountRequest("id", -100);

        this.mockMvc.perform(post(ACCOUNT_API_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

}