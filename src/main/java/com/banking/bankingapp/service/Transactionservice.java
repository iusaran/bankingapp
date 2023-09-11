package com.banking.bankingapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dao.AccountDao;
import com.banking.bankingapp.dao.TransactionDao;
import com.banking.bankingapp.dao.UserDao;
import com.banking.bankingapp.dto.Account;
import com.banking.bankingapp.dto.Transaction;
import com.banking.bankingapp.dto.Transactionstatus;
import com.banking.bankingapp.dto.User;
import com.banking.bankingapp.exception.AccountNotFound;
import com.banking.bankingapp.exception.TransactionTypeNotFound;
import com.banking.bankingapp.exception.UserNotFound;

@Service
public class Transactionservice {
	@Autowired
	TransactionDao tdao;
	@Autowired
	UserDao udao;
	@Autowired
	AccountDao adao;

	public ResponseEntity<ResponseStructure<Transaction>> saveTransaction(String uName, long ucontact, int transtype,
			double transamount) {
		User exuser = udao.loginUser(uName, ucontact);
		if (exuser != null) {
			Account exacc = exuser.getUserAccount();
			if (exacc != null) {
				if (transtype == 1) {
					Transaction t = new Transaction();
					t.setStatus(Transactionstatus.CREDITING);
					t.setTransactionAmount(transamount);
					t.setTransactionDate(LocalDate.now());
					tdao.saveTransaction(t);
					List<Transaction> trans = exacc.getAccountTransaction();
					trans.add(t);
					exacc.setAccountTransaction(trans);
					exacc.setAccountbalance(exacc.getAccountbalance() + transamount);
					adao.updateAccount(exacc, exacc.getAccountId());
					ResponseStructure<Transaction> structure = new ResponseStructure<>();
					structure.setMessage("Transaction sucess,amount credited");
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setData(t);
					return new ResponseEntity<ResponseStructure<Transaction>>(structure, HttpStatus.CREATED);
				} else if (transtype == 2) {
					Transaction t = new Transaction();
					t.setStatus(Transactionstatus.DEBITING);
					t.setTransactionAmount(transamount);
					t.setTransactionDate(LocalDate.now());
					tdao.saveTransaction(t);
					List<Transaction> trans = exacc.getAccountTransaction();
					trans.add(t);
					exacc.setAccountTransaction(trans);
					exacc.setAccountbalance(exacc.getAccountbalance() - transamount);
					adao.updateAccount(exacc, exacc.getAccountId());
					ResponseStructure<Transaction> structure = new ResponseStructure<>();
					structure.setMessage("Transaction sucess,amount debited");
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setData(t);
					return new ResponseEntity<ResponseStructure<Transaction>>(structure, HttpStatus.CREATED);
				}
				throw new TransactionTypeNotFound();
			}
			throw new AccountNotFound();
		}
       throw new UserNotFound();
	}
	
	public ResponseEntity<ResponseStructure<List<Transaction>>> findallTransaction(){
		ResponseStructure<List<Transaction>> structure = new ResponseStructure<>();
		structure.setMessage("Transaction sucess,amount debited");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(tdao.getAllTransaction());
		return new ResponseEntity<ResponseStructure<List<Transaction>>>(structure, HttpStatus.OK);
	}

}
