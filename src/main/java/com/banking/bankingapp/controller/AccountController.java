package com.banking.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dto.Account;
import com.banking.bankingapp.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService aservice;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Account>> saveAccount( @RequestParam String memail, @RequestParam String mpass, @RequestParam int userid,@RequestParam int acctype,@Valid @RequestBody Account account){
	   return	aservice.saveAccount(memail,mpass,userid,acctype,account);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Account>> findAccount(@RequestParam int id){
		return aservice.findAccount(id);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<ResponseStructure<Account>> deleteAccount(@RequestParam String uName,@Valid @RequestParam long ucontact,@RequestParam int accid,@RequestParam int bankid){
		return aservice.deleteAccount(uName, ucontact, accid,bankid);
	}

}
