package com.banking.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingapp.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {


}
