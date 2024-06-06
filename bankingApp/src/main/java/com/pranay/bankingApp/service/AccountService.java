package com.pranay.bankingApp.service;

import com.pranay.bankingApp.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDtu);

    AccountDto getAccountByID(Long id);

    void getDelete(Long id);


    AccountDto NameCorrection(Long id,String name);


    AccountDto deposit(Long id,double amount);

    AccountDto withdraw(Long id,double amount);

    List<AccountDto> allList();


}
