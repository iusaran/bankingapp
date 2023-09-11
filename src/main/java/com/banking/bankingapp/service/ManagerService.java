package com.banking.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.bankingapp.config.ResponseStructure;
import com.banking.bankingapp.dao.Bankdao;
import com.banking.bankingapp.dao.ManagerDao;
import com.banking.bankingapp.dto.Bank;
import com.banking.bankingapp.dto.Manager;

@Service
public class ManagerService {
	@Autowired
	ManagerDao mdao;
	@Autowired
	Bankdao bdao;
	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager m,int bankid)
	{
		ResponseStructure<Manager> structure =new ResponseStructure<>();
		structure.setMessage("Manager saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(mdao.saveManager(m));
		Bank bank=bdao.findBank(bankid);
		bank.setBankmanager(m);
		bdao.updateBank(bankid, bank);
		return new ResponseEntity<ResponseStructure<Manager>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Manager>> findManager(int mid)
	{
		ResponseStructure<Manager> structure =new ResponseStructure<>();
		structure.setMessage("Manager finded");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(mdao.findManager(mid));
		return new ResponseEntity<ResponseStructure<Manager>>(structure,HttpStatus.CREATED);
	}
	
	public Manager login(String memail,String mpass) {
    	return	mdao.login(memail,mpass);		
	}
	
	public ResponseEntity<ResponseStructure<Manager>> updateManager(Manager manager,int mid)
	{
		ResponseStructure<Manager> structure =new ResponseStructure<>();
		structure.setMessage("Manager finded");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(mdao.updateManager(manager,mid));
		return new ResponseEntity<ResponseStructure<Manager>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(int mid,int bid)
	{
		Bank exbank=bdao.findBank(bid);
		exbank.setBankmanager(null);
		bdao.updateBank(bid, exbank);
		ResponseStructure<Manager> structure =new ResponseStructure<>();
		structure.setMessage("Manager deleted");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(mdao.deleteManager(mid));
		return new ResponseEntity<ResponseStructure<Manager>>(structure,HttpStatus.OK);
	}
}
