package com.banking.bankingapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.bankingapp.dto.Account;
import com.banking.bankingapp.exception.AccountNotFound;
import com.banking.bankingapp.repo.AccountRepo;

@Repository
public class AccountDao {
	@Autowired
	AccountRepo repo;
	
	public Account saveAccount(Account account) {
		return repo.save(account);
	}

	public Account findAccount(int aid) {
		Optional<Account> optional=repo.findById(aid);
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new AccountNotFound();
	}
	
	public Account deleteAccount(int aid) {
		Account account=findAccount(aid);
		if(account!=null) {
		 repo.delete(account);
		 return account;
		}
		throw new AccountNotFound();
	}
	
	public Account updateAccount(Account acc,int accid) {
		Account exacc=findAccount(accid);
		if(exacc!=null) {
		return	repo.save(acc);
		}
		throw new AccountNotFound();
	}
}
