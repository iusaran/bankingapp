package com.banking.bankingapp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="BankName can't be blank")
	@NotNull(message="BankName can't be null")
	private String bankName;
	
	@NotBlank(message="BankIfsc can't be blank")
	@NotNull(message="BankIfsc can't be null")
	private String bankIfsc;
	
	@NotBlank(message="BankLocation can't be blank")
	@NotNull(message="BankLocation can't be null")
	private String bankLocation;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> users;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Manager bankmanager;
}
