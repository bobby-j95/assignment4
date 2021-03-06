package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*This is the main Bank Account class that will be the parent to the other bank count classes:
 * SavingsAccount, CheckingAccount, and CDAccount
 * Created by: Robert Johns
 * Edited by: Eliana Cristina Betancur
 */
abstract class BankAccount {

	// All private variables needed in the program
	protected double balance;
	protected double interestRate;
	protected long accountNumber;
	Date openDate;
	public List<Transaction> transactions = new ArrayList<Transaction>();

	// first constructor without account number
	public BankAccount(double balance, double interestRate) {
		this(MeritBank.getNextAccountNumber(), balance, interestRate);
	}

	// second constructor with account number
	public BankAccount(long accountNumber, double balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public BankAccount(long accountNumber2, double openBalance, double interestRate2, Date accountOpenedOn) {
		// TODO Auto-generated constructor stub
		this.accountNumber = accountNumber2;
		this.balance = openBalance;
		this.interestRate = interestRate2;
		this.openDate = accountOpenedOn;
	}

	// getter for account number
	public long getAccountNumber() {
		return this.accountNumber;
	}

	// getter for balance
	public double getBalance() {
		return this.balance;
	}

	// getter for interest rate
	public double getInterestRate() {
		return this.interestRate;
	}

	public Date getOpenedOn() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	// checks if amount can be withdrawn if its over balance or negative
	public boolean withdraw(double amount) {
		if ((amount <= balance) && (amount > 0)) {
			balance -= amount;
			return true;
		} else {
			return false;
		}

	}

	// checks if amount is negative
	public boolean deposit(double amount) {
		if ((amount > 0)) {
			balance += amount;
			return true;
		} else {
			return false;
		}

	}

	// returns the value of the account after a set amount of years
	public double futureValue(int years) {
		return MeritBank.recursiveFutureValue( balance, years, interestRate);
	}

	public String writeToString() {
		StringBuilder accountData = new StringBuilder();
		accountData.append(accountNumber).append(",");
		accountData.append(openDate).append(",");
		accountData.append(balance).append(",");
		accountData.append(interestRate);
		return accountData.toString();
		
		
	}
	
//	public static BankAccount readFromString(String accountData) throws ParseException, NumberFormatException {
//	    try {
//	    	String [] holding = accountData.split(",");
//	    	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
//	    	Long accountNumber = Long.parseLong(holding[0]);
//	        double balance = Double.parseDouble(holding[1]);
//	        double interestRate = Double.parseDouble(holding[2]);
//	        Date accountOpenedOn = date.parse(holding[3]);
//	        return new BankAccount(accountNumber, balance, interestRate, accountOpenedOn);
//	    		
//	    }
//	    catch(ParseException  e) {
//	    	e.printStackTrace();
//	    	return null;
//	    }
//	    catch(NumberFormatException e) {
//	    	e.printStackTrace();
//	    	return null;
//	    }
//			
//	}
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
		
	}
	public List<Transaction> getTransactions(){
		return transactions;
		
	}
}
