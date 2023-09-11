package com.banking.bankingapp.exception;

public class UserContactNotFound extends RuntimeException{
	
	private String message="User contact not match";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
