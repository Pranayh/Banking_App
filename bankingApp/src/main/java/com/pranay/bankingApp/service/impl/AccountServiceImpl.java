package com.pranay.bankingApp.service.impl;

import com.pranay.bankingApp.dto.AccountDto;
import com.pranay.bankingApp.entity.Account;
import com.pranay.bankingApp.mapper.AccountMapper;
import com.pranay.bankingApp.repository.AccountRepository;
import com.pranay.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
    return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountByID(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't Exit"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public void getDelete(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't Exit"));
        accountRepository.deleteById( id);
    }



//    @Override
//    public AccountDto getUpdated(Long id,AccountDto accountDto) {
//        Account account= AccountMapper.mapToAccount(accountDto);
//        Account savedAccount=accountRepository.save(account);
//        return AccountMapper.mapToAccountDto(savedAccount);
//    }


    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't Exit"));

        double total = account.getBalance()+ amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't Exit"));

        if(amount > account.getBalance()){
            throw new RuntimeException("Insufficient balance in your account");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public List<AccountDto> allList() {

        List<Account> account = accountRepository.findAll();

        return account.stream().map((account1) -> AccountMapper.mapToAccountDto(account1)).collect(Collectors.toList());
    }

    @Override
    public AccountDto NameCorrection(Long id, String name) {

        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account doesn't Exit"));


        account.setAccountHolderName(name);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
