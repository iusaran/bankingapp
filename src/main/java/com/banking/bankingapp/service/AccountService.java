package com.banking.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dao.AccountDao;
import com.banking.bankingapp.dao.Bankdao;
import com.banking.bankingapp.dao.UserDao;
import com.banking.bankingapp.dto.Account;
import com.banking.bankingapp.dto.Accounttype;
import com.banking.bankingapp.dto.Bank;
import com.banking.bankingapp.dto.Manager;
import com.banking.bankingapp.dto.User;
import com.banking.bankingapp.exception.AccountNotFound;
import com.banking.bankingapp.exception.AccountTypeNotFound;
import com.banking.bankingapp.exception.ManagerNotfound;
import com.banking.bankingapp.exception.UserNotFound;

@Service
public class AccountService {
	@Autowired
	AccountDao adao;
	@Autowired
	ManagerService mservice;
	@Autowired
	UserService uservice;
	@Autowired
	UserDao udao;
	@Autowired
	Bankdao bdao;

	public ResponseEntity<ResponseStructure<Account>> saveAccount(String memail, String mpass, int userid, int acctype,
			Account account) {
		Manager manager = mservice.login(memail, mpass);
		User exuser = udao.findUser(userid);
		if (exuser != null) {
			if (manager != null) {
				account.setUser(exuser);
				if (acctype <= 3) {
					if (acctype == 1) {
						account.setType(Accounttype.SAVINGS);
					} else if (acctype == 2) {
						account.setType(Accounttype.CURRENT);
					} else if (acctype == 3) {
						account.setType(Accounttype.LOAN);
					}
					ResponseStructure<Account> structure = new ResponseStructure<>();
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMessage("Account created");
					structure.setData(adao.saveAccount(account));
					exuser.setUserAccount(account);
					udao.updateUser(userid, exuser);
					return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.CREATED);
				}
				throw new AccountTypeNotFound();
			}
			throw new ManagerNotfound();
		}
		throw new UserNotFound();
	}

	public ResponseEntity<ResponseStructure<Account>> findAccount(int id) {
		ResponseStructure<Account> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage("Account finded");
		structure.setData(adao.findAccount(id));
		return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Account>> deleteAccount(String uName, long ucontact, int accid,
			int bankid) {
		User exuser = udao.loginUser(uName, ucontact);
		if (exuser != null) {
			Account acc = adao.findAccount(accid);
			if (acc != null) {
				User user = acc.getUser();
				Bank exbank = bdao.findBank(bankid);
				List<User> users = exbank.getUsers();
				users.remove(user);
				exbank.setUsers(users);
				bdao.updateBank(bankid, exbank);

				ResponseStructure<Account> structure = new ResponseStructure<>();
				structure.setStatus(HttpStatus.OK.value());
				structure.setMessage("Account deleted");
				structure.setData(adao.deleteAccount(accid));
				return new ResponseEntity<ResponseStructure<Account>>(structure, HttpStatus.OK);
			}
			throw new AccountNotFound();
		}
		throw new UserNotFound();

	}

}
