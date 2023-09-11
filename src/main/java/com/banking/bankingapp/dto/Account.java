package com.banking.bankingapp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	
//	@Column (name = "accountNumber", unique = true)
	private int accountNumber;
	
	private Accounttype type;
	
	private double accountbalance;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Transaction> accountTransaction;
}
