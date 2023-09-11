package com.banking.bankingapp.exception;

public class ManagerEmailNotMatch extends RuntimeException {
	
	private String messagae="Manager email not match";

	public String getMessagae() {
		return messagae;
	}

	public void setMessagae(String messagae) {
		this.messagae = messagae;
	} 

}
