package com.banking.bankingapp.exception;

public class AccountTypeNotFound extends RuntimeException{
	
	private String message="AccountType invalid should be less than 3";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
