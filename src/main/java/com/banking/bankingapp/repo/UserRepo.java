package com.banking.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banking.bankingapp.dto.Manager;
import com.banking.bankingapp.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userName=?1")
	public User getByName(String userName) ;
	
	@Query("select u from User u where u.userName=?1 and u.userContact=?2")
	public Manager getByContact(String uname,long ucontact) ;
}
