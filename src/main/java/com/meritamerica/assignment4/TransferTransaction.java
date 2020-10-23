package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction{

	private BankAccount sourceAccount;
	private BankAccount targetAccount;
	private double amount;
	
	@Override
	public void process() throws NegativeAmountException, 
			ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
	
	public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
	}

}
