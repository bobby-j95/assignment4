package com.meritamerica.assignment4;

import java.util.Date;

public class DepositTransaction extends Transaction {

	private BankAccount targetAccount;
	private double amount;

	public DepositTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.openDate = new Date();
	}
	
	@Override
	public void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// TODO Auto-generated method stub
		if (amount >= 1000) { // all if statements are redundant, it was asked to be added in the MeritBank
			FraudQueue.addTransaction(this);
			throw new ExceedsFraudSuspicionLimitException();
		} else if (amount < 0) {
			throw new NegativeAmountException();
		} else {
			targetAccount.deposit(amount);
		}
	}
<<<<<<< HEAD
	
	public DepositTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
//		try {
//			process();
//		} catch (NegativeAmountException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExceedsAvailableBalanceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExceedsFraudSuspicionLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
	//	} 
		this.openDate = new Date();
	}
=======
>>>>>>> 4d5d8d487b2d81c970b57934c3bd55cc49855818

}
