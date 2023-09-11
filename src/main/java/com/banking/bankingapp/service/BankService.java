package com.banking.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dao.Bankdao;
import com.banking.bankingapp.dao.ManagerDao;
import com.banking.bankingapp.dao.UserDao;
import com.banking.bankingapp.dto.Bank;
import com.banking.bankingapp.dto.Manager;
import com.banking.bankingapp.dto.User;
import com.banking.bankingapp.exception.ManagerNotfound;

@Service
public class BankService {

	@Autowired
	Bankdao bdao;
	@Autowired
	ManagerDao mdao;
	@Autowired
	UserDao udao;

	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank b) {
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setMessage("Bank saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(bdao.saveBank(b));
		return new ResponseEntity<ResponseStructure<Bank>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Bank>> findBank(int bid) {
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setMessage("Bank finded");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(bdao.findBank(bid));
		return new ResponseEntity<ResponseStructure<Bank>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Bank>> updateBank(int bid, Bank bank) {
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setMessage("Udated succesfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(bdao.updateBank(bid, bank));
		return new ResponseEntity<ResponseStructure<Bank>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<User>>> findallUser(String email, String pass) {
		Manager exiManager = mdao.login(email, pass);
		if (exiManager != null) {
			ResponseStructure<List<User>> structure = new ResponseStructure<List<User>>();
			structure.setMessage("all user showned");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(udao.findallUser());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.CREATED);
		}
		throw new ManagerNotfound();
	}
	public ResponseEntity<ResponseStructure<Bank>> deleteBank(int bankid) {
		ResponseStructure<Bank> structure = new ResponseStructure<>();
		structure.setMessage("Bank deleted");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(bdao.deleteBank(bankid));
		return new ResponseEntity<ResponseStructure<Bank>>(structure, HttpStatus.OK);
	}
}
