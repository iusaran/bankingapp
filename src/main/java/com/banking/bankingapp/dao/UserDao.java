package com.banking.bankingapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.bankingapp.dto.User;
import com.banking.bankingapp.exception.UserContactNotFound;
import com.banking.bankingapp.exception.UserNameNotFound;
import com.banking.bankingapp.exception.UserNotFound;
import com.banking.bankingapp.repo.UserRepo;

@Repository
public class UserDao {
	@Autowired
	UserRepo repo;

	public User saveUser(User user) {
		return repo.save(user);
	}

	public User findUser(int id) {
		Optional<User> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new UserNotFound();
	}

	public User deleteUser(int id) {
		User exiuser = findUser(id);
		if (exiuser != null) {
			repo.delete(exiuser);
			return exiuser;
		}
		throw new UserNotFound();
	}

	public User updateUser(int userid, User user) {
		User exuser = findUser(userid);
		if (exuser != null) {
			if (user.getUserAddress() == null) {
				user.setUserAddress(exuser.getUserAddress());
			}
			if (user.getUserContact() == 0) {
				user.setUserContact(exuser.getUserContact());
			}
			if (user.getUserName() == null) {
				user.setUserName(exuser.getUserName());
			}
			if (user.getUserAccount() == null) {
				//exuser.getUserAccount().setUser(null);
				user.setUserAccount(exuser.getUserAccount());
			}
			user.setUserId(userid);
			repo.save(user);
			return user;
		}
		throw new UserNotFound(); 
	}

	public List<User> findallUser() {
		return repo.findAll();
	}

	public User loginUser(String uname, long contact) {
		User user = repo.getByName(uname);
		if (user != null) {
			if (user.getUserContact() == contact) {
				return user;
			}
			throw new UserContactNotFound();
		}
		throw  new UserNameNotFound();
	}
}
