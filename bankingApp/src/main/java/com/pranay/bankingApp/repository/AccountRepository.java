package com.pranay.bankingApp.repository;

import com.pranay.bankingApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account,Long> {
}
