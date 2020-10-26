package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount {
	private CDOffering offering = null;
	private Date startDate;
	private double balance;
	private int term;

	public CDAccount(CDOffering offering, double balance) {
		super(balance, offering.getInterestRate());
		this.balance = balance;
		this.offering = offering;
		this.startDate = new Date();
	}

	public CDAccount(long accountNumber, double openBalance, double interestRate, Date accountOpenedOn, int term) {
		super(accountNumber, openBalance, interestRate, accountOpenedOn);
		this.balance = openBalance;
		System.out.println(balance + ":)");
		this.term = term;
	}

	public double getBalance() {
		return super.getBalance();
	}

	public double getInterestRate() {
		return super.getInterestRate();
	}

	public int getTerm() {
		return term;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public long getAccountNumber() {
		return super.getAccountNumber();
	}

	public boolean withdraw(double amount) {
		Date date = new Date();  
		int years = startDate.getYear() - date.getYear();
		Transaction t = new WithdrawTransaction(this, amount);
		if(years > term) {
	        return true;
		}
		return false;
	}

	public boolean deposit(double amount) {
		Date date = new Date();
		int years = startDate.getYear() - date.getYear();
		Transaction t = new DepositTransaction(this, amount);
		if (years > term) {
			return true;
		}
		return false;
	}

	public double futureValue() {
		return MeritBank.recursiveFutureValue(super.getBalance(), offering.getTerm(), offering.getInterestRate());
	}

	public static CDAccount readFromString(String accountData) throws ParseException, NumberFormatException {
		String[] holding = accountData.split(",");
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		long accountNumber = Long.parseLong(holding[0]);
		double balance = Double.parseDouble(holding[1]);
		double interestRate = Double.parseDouble(holding[2]);
		Date accountOpenedOn = date.parse(holding[3]);
		int term = Integer.parseInt(holding[4]);
		CDAccount newCDAccount = new CDAccount(accountNumber, balance, interestRate, accountOpenedOn, term);
		return newCDAccount;
	}

	public String writeToString() {
		StringBuilder override = new StringBuilder();
		override.append(writeToString()).append(",");
		override.append(getTerm());
		return override.toString();
	}

}