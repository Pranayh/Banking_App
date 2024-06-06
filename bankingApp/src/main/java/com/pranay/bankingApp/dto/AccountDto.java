package com.pranay.bankingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class AccountDto {
    private long id;
    private String accountHolderName;
    private double balance;



}
