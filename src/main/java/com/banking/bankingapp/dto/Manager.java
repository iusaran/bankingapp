package com.banking.bankingapp.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	
	@NotBlank(message="managerName can't be blank")
	@NotNull(message="managerName can't be null")
	private String managerName;
	
	@NotBlank(message="managerEmail can't be blank")
	@NotNull(message="managerEmail can't be null")
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String managerEmail;
	
	@NotBlank(message="managerPass can't be blank")
	@NotNull(message="managerPass can't be null")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message="atleast one everything")
	private String managerPass;

	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	@Column (name = "managerContact", unique = true)
	private long managerContact;
	

}
