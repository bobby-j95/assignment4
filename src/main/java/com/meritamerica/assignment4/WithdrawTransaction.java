package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction{

	private BankAccount targetAccount;
	private double amount;
	
	@Override
	public void process() throws NegativeAmountException, 
			ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
	
	public WithdrawTransaction(BankAccount targetAccount, double amount){
		this.targetAccount = targetAccount;
		this.amount = amount;
	}

}
