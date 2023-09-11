package com.banking.bankingapp.exception;

public class BankNotFound extends RuntimeException{

	private String message="Bank not found";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
