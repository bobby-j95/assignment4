package com.meritamerica.assignment4;

public class ExceedsFraudSuspicionLimitException extends Exception {

	private double amount;

	
	public ExceedsFraudSuspicionLimitException() {}
	
	public ExceedsFraudSuspicionLimitException(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}
	
	
	
}
