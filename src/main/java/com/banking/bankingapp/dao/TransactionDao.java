package com.banking.bankingapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.bankingapp.dto.Transaction;
import com.banking.bankingapp.repo.TransactionRepo;

@Repository
public class TransactionDao {
	@Autowired
	TransactionRepo repo;

	public Transaction saveTransaction(Transaction transaction) {
		return repo.save(transaction);
	}

	public List<Transaction> getAllTransaction(){
		return repo.findAll();	
	}
}
