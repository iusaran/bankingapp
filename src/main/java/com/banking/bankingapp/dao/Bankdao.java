package com.banking.bankingapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.bankingapp.dto.Bank;
import com.banking.bankingapp.exception.BankNotFound;
import com.banking.bankingapp.repo.BankRepo;

@Repository
public class Bankdao {
	@Autowired
	BankRepo repo;

	public Bank saveBank(Bank bank) {
		return repo.save(bank);
	}

	public Bank findBank(int bid) {
		Optional<Bank> optional = repo.findById(bid);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new BankNotFound();
	}

	public Bank updateBank(int bankid, Bank bank) {
		Bank exibank = findBank(bankid);
		if (exibank != null) {
			if (bank.getBankIfsc() == null) {
				bank.setBankIfsc(exibank.getBankIfsc());
			}
			if (bank.getBankLocation() == null) {
				bank.setBankLocation(exibank.getBankLocation());
			}
			if (bank.getBankmanager() == null) {
				bank.setBankmanager(exibank.getBankmanager());
			}
			if (bank.getBankName() == null) {
				bank.setBankName(exibank.getBankName());
			}
			if (bank.getUsers() == null) {
				bank.setUsers(exibank.getUsers());
			}
			bank.setId(bankid);
			repo.save(bank);
			return bank;
		}
		throw new BankNotFound();
	}

	public Bank deleteBank(int bid) {
		Bank exibank = findBank(bid);
		if (exibank != null) {
			repo.delete(exibank);
			return exibank;
		}
		throw new BankNotFound();
	}
}
