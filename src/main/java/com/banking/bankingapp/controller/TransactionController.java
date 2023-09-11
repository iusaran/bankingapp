package com.banking.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dto.Transaction;
import com.banking.bankingapp.service.Transactionservice;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	Transactionservice tservice;

	@PostMapping
	public ResponseEntity<ResponseStructure<Transaction>> saveTransaction(@RequestParam String uName,@RequestParam long ucontact,@RequestParam int transtype,@RequestParam double transamount){
		return tservice.saveTransaction(uName, ucontact, transtype, transamount);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Transaction>>> findallTransaction(){
		return tservice.findallTransaction();
	}
}
