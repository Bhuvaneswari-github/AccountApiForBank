package com.ramco.bank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCustomerDto {

    private String id;

    private String name;

    private String surname;


}
