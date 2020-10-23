package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction{

	@Override
	public void process() throws NegativeAmountException, 
			ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		
	}
	
	public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount){
		
	}

}
