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
import com.banking.bankingapp.dto.Manager;
import com.banking.bankingapp.service.ManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	ManagerService mservice;

	@PostMapping
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@Valid @RequestBody Manager manager,@RequestParam int bankid){
		return mservice.saveManager(manager,bankid);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Manager>> findManager(@RequestParam int mid){
		return mservice.findManager(mid);
	}
	
	@GetMapping("/login")
	public Manager login(@RequestParam String memail,@RequestParam String mpass) {
		return mservice.login(memail,mpass);
	}
	
	@GetMapping("/update")
	public ResponseEntity<ResponseStructure<Manager>> updateManager(@Valid @RequestBody Manager manager,@RequestParam int mid){
		return mservice.updateManager(manager, mid);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(@RequestParam int mid,@RequestParam int bid){
		return mservice.deleteManager(mid,bid);
	}
}
