package com.banking.bankingapp.exception;

public class UserNameNotFound extends RuntimeException{
	
	private String message="User name not match";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
