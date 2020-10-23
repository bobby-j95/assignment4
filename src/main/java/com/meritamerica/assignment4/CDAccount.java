package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount{
	private CDOffering offering = null;
	private Date startDate;
	private double balance;
	private int term =0;
	
	//*created by behulum w
	
	CDAccount(CDOffering offering, double balance){
		super(balance, offering.getInterestRate());
		this.offering = offering;
		this.startDate= new Date();
	}
	
	public CDAccount(long accountNumber, double openBalance, double interestRate, Date accountOpenedOn, int term) {
		super(accountNumber, openBalance, interestRate, accountOpenedOn);
		this.term = term;
	}

	public double getBalance() {
		return super.getBalance();
	}
	
	public double getInterestRate() {
		return super.interestRate;
	}
	
	public int getTerm() {
		return term;
	}
	
	public Date getStartDate(){
		return this.startDate;
	}

	// created by behulum w
	
	public long getAccountNumber() {
		return super.accountNumber;
	}
	
	public boolean withdraw(double amount) {
        return false;
    }
    
    public boolean deposit(double amount) {
    	return false;
    }
	
	public double futureValue() {
		return (super.getBalance() * Math.pow(1.0 + offering.getInterestRate(), offering.getTerm()));
		
		
	}

	public static CDAccount readFromString(String accountData)throws ParseException, NumberFormatException {
    	String [] holding = accountData.split(",");
    	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    	long accountNumber = Long.parseLong(holding[0]);
    	double balance = Double.parseDouble(holding[1]);
    	double interestRate = Double.parseDouble(holding[2]);
    	Date accountOpenedOn = date.parse(holding[3]);
    	int term = Integer.parseInt(holding[4]);
    	CDAccount newCDAccount = new CDAccount(accountNumber,balance,interestRate,accountOpenedOn,term);
    	return newCDAccount;
    }
    
    public String writeToString() {
    	StringBuilder override = new StringBuilder();
    	override.append(writeToString()).append(",");
    	override.append(getTerm());
    	return override.toString();
    }

}