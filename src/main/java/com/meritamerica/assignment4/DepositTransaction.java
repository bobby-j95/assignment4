package com.meritamerica.assignment4;

import java.util.Date;

public class DepositTransaction extends Transaction {

	private BankAccount targetAccount;
	private double amount;
	
	@Override
	public void process() throws NegativeAmountException, 
			ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		if(amount >= 1000) {
			FraudQueue.addTransaction(this);
			throw new ExceedsFraudSuspicionLimitException();
		} else if(amount < 0){
			throw new NegativeAmountException();
		}else if(amount > targetAccount.getBalance()){
			throw new ExceedsAvailableBalanceException();
		}else {
			targetAccount.deposit(amount);
		}
	}
	
	public DepositTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.openDate = new Date();
	}

}
