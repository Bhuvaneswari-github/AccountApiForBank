package com.ramco.bank.account;

import java.time.Clock;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ramco.bank.account.model.Customer;
import com.ramco.bank.account.repository.CustomerRepository;

@SpringBootApplication
public class AccountServiceApplication implements CommandLineRunner {
	 private final CustomerRepository customerRepository;

	    public AccountServiceApplication(CustomerRepository customerRepository) {
	        this.customerRepository = customerRepository;
	    }
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	  @Bean
	    public Clock clock() {
	        return Clock.systemUTC();
	    }


	    @Override
	    public void run(String... args) throws Exception {
	        Customer customer = customerRepository.save(new Customer("", "Bhuvana", "Eswari", new HashSet<>()));
	        System.out.println(customer);
	    }
}
