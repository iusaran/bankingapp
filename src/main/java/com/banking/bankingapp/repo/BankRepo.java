package com.banking.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingapp.dto.Bank;

public interface BankRepo extends JpaRepository<Bank, Integer> {

}
