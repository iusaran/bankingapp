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
import com.banking.bankingapp.dto.User;
import com.banking.bankingapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService uservice;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user,@RequestParam int bankid) {	
		return uservice.saveUser(user, bankid);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser(@RequestParam int id) {
		return uservice.findUser(id);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int userid,@RequestParam int bankid) {
		return uservice.deleteUser(userid, bankid);
	}
	
	@GetMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam int uid,@RequestBody User user) {
		return uservice.updateUser(uid, user);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser(){
		return uservice.findallUser();
	}
	@GetMapping("/updatenumber")
	public ResponseEntity<ResponseStructure<User>> updateNumber(@RequestParam int uid,@RequestParam long number){
		return uservice.updateNumber(uid, number);
	}
	
	@GetMapping("/login")
	public User loginUser(@RequestParam String uname,@RequestParam long contact) {
		return uservice.loginUser(uname, contact);
	}
}
