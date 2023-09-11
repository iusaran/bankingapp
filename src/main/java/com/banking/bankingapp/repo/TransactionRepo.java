package com.banking.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.bankingapp.dto.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
