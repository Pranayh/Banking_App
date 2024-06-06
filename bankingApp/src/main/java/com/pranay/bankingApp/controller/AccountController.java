package com.pranay.bankingApp.controller;


import com.pranay.bankingApp.dto.AccountDto;
import com.pranay.bankingApp.entity.Account;
import com.pranay.bankingApp.service.AccountService;
import com.pranay.bankingApp.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    // Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> getSave(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountByID(id);
        return ResponseEntity.ok(accountDto);
    }


    // Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteByid(@PathVariable Long id){
        accountService.getDelete(id);
        return ResponseEntity.ok("Account is Deleted Successfully");
    }




    // Deposit Amount to the Account

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit( @PathVariable Long id, @RequestBody Map<String, Double> request){


        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }



    // Withdraw amount from the Account
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdrawAmount( @PathVariable Long id, @RequestBody Map<String, Double> request){


        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }



    // Get All Accounts
    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDto>> getAll(){

        List<AccountDto> accountDtoList= accountService.allList();

        return ResponseEntity.ok(accountDtoList);
    }


    @PutMapping("/nameChange/{id}")
    public ResponseEntity<AccountDto> nameChange( @PathVariable Long id, @RequestBody Map<String, String> request){


        String newName= request.get("accountHolderName");
        AccountDto accountDto = accountService.NameCorrection(id,newName);
        return ResponseEntity.ok(accountDto);
    }

}
