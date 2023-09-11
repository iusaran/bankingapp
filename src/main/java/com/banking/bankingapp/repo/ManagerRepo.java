package com.banking.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banking.bankingapp.dto.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {
	
	@Query("select m from Manager m where m.managerEmail=?1")
	public Manager getByEmail(String memail) ;
	
	@Query("select m from Manager m where m.managerEmail=?1 and m.managerPass=?2")
	public Manager getByPass(String memail,String mpass) ;

}
