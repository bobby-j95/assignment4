package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction {
	
	private BankAccount sourceAccount;
	private BankAccount targetAccount;
	private double amount;
	private String reason;
	private Date openDate;

	public BankAccount getSourceAccount() {
		return sourceAccount;
	}
	
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	public BankAccount getTargetAccount() {
		return targetAccount;
	}
	
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public java.util.Date getTransactionDate(){
		return openDate;
	}
	
	public void setTransactionDate(java.util.Date date) {
		openDate = date;
	}
	
	public String writeToString() {
		
	}
	
	public static Transaction readFromString(String transactionDataString) throws 
		ParseException, NumberFormatException{
		try {
	    	String [] holding = accountData.split(",");
	    	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	    	Long accountNumber = Long.parseLong(holding[0]);
	        double balance = Double.parseDouble(holding[1]);
	        double interestRate = Double.parseDouble(holding[2]);
	        Date accountOpenedOn = date.parse(holding[3]);
	        return new BankAccount(accountNumber, balance, interestRate, accountOpenedOn);
	    		
	    }
	    catch(ParseException  e) {
	    	e.printStackTrace();
	    	return null;
	    }
	    catch(NumberFormatException e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
	public abstract void process() throws NegativeAmountException, 
		ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	
	public boolean isProcessedByFraudTeam() {
		
	}
	
	public void setProcessedByFraudTeam(boolean isProcessed) {
		
	}
	
	public String getRejectionReason() {
		return reason;
	}
	
	public void setRejectionReason(String reason) {
		this.reason = reason;
	}

}
