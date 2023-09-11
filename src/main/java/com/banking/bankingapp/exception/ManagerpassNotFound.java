package com.banking.bankingapp.exception;

public class ManagerpassNotFound extends RuntimeException{
	
	private String message="Manager password  not Match";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
