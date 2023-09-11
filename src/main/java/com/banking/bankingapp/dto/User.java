package com.banking.bankingapp.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message="user name can't be blank")
	@NotNull(message="user name can't be null")
	private String userName;
	
	@Positive
	@Min(value=6000000000l)
	@Max(value=9999999999l)
	@Column (name = "userContact", unique = true)
	private long userContact;
	
	@NotBlank(message="user address can't be blank")
	@NotNull(message="user address can't be null")
	private String userAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Account userAccount;
}
