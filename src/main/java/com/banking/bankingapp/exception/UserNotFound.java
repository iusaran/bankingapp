package com.banking.bankingapp.exception;

public class UserNotFound extends RuntimeException {
	private String message="user not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
