package com.banking.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dto.Bank;
import com.banking.bankingapp.dto.User;
import com.banking.bankingapp.service.BankService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	BankService bservice;

	@PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@Valid @RequestBody Bank bank) {
		return bservice.saveBank(bank);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Bank>> findBank(@RequestParam int bid) {
		return bservice.findBank(bid);
	}

	@GetMapping("/update")
	public ResponseEntity<ResponseStructure<Bank>> updateBank(@RequestParam int bid, @RequestBody Bank bank) {
		return bservice.updateBank(bid, bank);
	}

	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<User>>> findallUser(@Valid @RequestParam String memail,
			@Valid @RequestParam String mpass) {
		return bservice.findallUser(memail, mpass);
	}

	@GetMapping("/delete")
	public ResponseEntity<ResponseStructure<Bank>> deleteBank(@RequestParam int bankid) {
		return bservice.deleteBank(bankid);
	}

}
