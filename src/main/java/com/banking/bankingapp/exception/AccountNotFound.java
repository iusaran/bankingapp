package com.banking.bankingapp.exception;

public class AccountNotFound extends RuntimeException{
	
	private String message="Account not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
