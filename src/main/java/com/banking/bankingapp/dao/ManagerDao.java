package com.banking.bankingapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.banking.bankingapp.dto.Manager;
import com.banking.bankingapp.exception.ManagerEmailNotMatch;
import com.banking.bankingapp.exception.ManagerNotfound;
import com.banking.bankingapp.exception.ManagerpassNotFound;
import com.banking.bankingapp.repo.ManagerRepo;

@Repository
public class ManagerDao {
	@Autowired
	ManagerRepo repo;

	public Manager saveManager(Manager manager) {
		return repo.save(manager);
	}

	public Manager findManager(int mid) {
		Optional<Manager> optional = repo.findById(mid);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new ManagerNotfound();
	}

	public Manager login(String memail, String mpass) {
		Manager manager = repo.getByEmail(memail);
		if (manager != null) {
			if (manager.getManagerPass().equals(mpass)) {
				return manager;
			}
			throw new ManagerpassNotFound();
		}
		throw new ManagerEmailNotMatch();
	}

	public Manager updateManager(Manager manager, int mid) {
		Manager exmanager = findManager(mid);
		if (exmanager != null) {
			if(manager.getManagerContact()==0) {
			manager.setManagerContact(exmanager.getManagerContact());
			}
			if(manager.getManagerEmail()==null) {
				manager.setManagerEmail(exmanager.getManagerEmail());
			}
			if(manager.getManagerName()==null) {
				manager.setManagerName(exmanager.getManagerName());
			}
			if(manager.getManagerPass()==null) {
				manager.setManagerPass(exmanager.getManagerPass());
			}
			manager.setManagerId(mid);
			return repo.save(manager);
		}
		throw new ManagerNotfound();
	}

	public Manager deleteManager(int mid) {
		Manager exmanager = findManager(mid);
		if (exmanager != null) {
			repo.delete(exmanager);
			return exmanager;
		}
		throw new ManagerNotfound();
	}

}
