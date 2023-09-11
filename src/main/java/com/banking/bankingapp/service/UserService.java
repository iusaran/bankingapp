package com.banking.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dao.Bankdao;
import com.banking.bankingapp.dao.UserDao;
import com.banking.bankingapp.dto.Bank;
import com.banking.bankingapp.dto.User;
import com.banking.bankingapp.exception.BankNotFound;
import com.banking.bankingapp.exception.UserNotFound;

@Service
public class UserService {
	@Autowired
	UserDao udao;
	@Autowired
	Bankdao bdao;

	//	public ResponseStructure<User> saveUser(User u) {
	//		ResponseStructure<User> structure =new ResponseStructure<>();
	//		structure.setMessage("user saved");
	//		structure.setStatus(HttpStatus.CREATED.value());
	//		structure.setData(udao.saveUser(u));
	//		return structure;
	//	}

	//still save method have some issue so we move to Reponse entity with show the same 
	//same status using ResponseEntity we solve the issue

public ResponseEntity<ResponseStructure<User>> saveUser(User u, int bankid) {
		Bank bank = bdao.findBank(bankid);
		if(bank!=null) {
		List<User> user = bank.getUsers();
		user.add(u);
		bank.setUsers(user);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setMessage("user saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(udao.saveUser(u));
		bdao.updateBank(bankid, bank);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
		}
		throw new BankNotFound();
	}

public ResponseEntity<ResponseStructure<User>> findUser(int id) {
		User exuser = udao.findUser(id);
		if (exuser != null) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("user found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(exuser);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFound();
	}

public ResponseEntity<ResponseStructure<User>> deleteUser(int userid, int bankid) {
		Bank bank = bdao.findBank(bankid);
		User exuser = udao.findUser(userid);
		if (exuser != null) {
			if (bank != null) {
				List<User> users = bank.getUsers();
				users.remove(exuser);
				bank.setUsers(users);
				ResponseStructure<User> structure = new ResponseStructure<>();
				structure.setMessage("user deleted");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(udao.deleteUser(userid));
				bdao.updateBank(bankid, bank);
				return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
			}
			throw new BankNotFound();
		}
		throw new UserNotFound();
	}

public ResponseEntity<ResponseStructure<User>> updateUser(int uid, User user) {
			ResponseStructure<User> structure = new ResponseStructure<>();
			structure.setMessage("user updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(udao.updateUser(uid, user));
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}

public ResponseEntity<ResponseStructure<List<User>>> findallUser() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setMessage("user showned");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(udao.findallUser());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}

public ResponseEntity<ResponseStructure<User>> updateNumber(int id, long number) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		User exiuser = udao.findUser(id);
		exiuser.setUserContact(number);
		User newuser = udao.updateUser(id, exiuser);
		structure.setMessage("Number updated success");
		structure.setStatus(HttpStatus.ACCEPTED.value());
		structure.setData(newuser);
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}

public User loginUser(String uname, long contact) {
		return udao.loginUser(uname, contact);
	}
}
