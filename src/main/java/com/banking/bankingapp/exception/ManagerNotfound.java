package com.banking.bankingapp.exception;

public class ManagerNotfound extends RuntimeException{
	
	private String message="Manager not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
