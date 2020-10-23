package com.meritamerica.assignment4;

import java.util.ArrayList;

public class FraudQueue {
	
	private ArrayList<Transaction> queue;
	
	public FraudQueue() {
		
	}
	
	public void addTransaction(Transaction transaction) {
		queue.add(transaction);
	}
	public Transaction getTransaction() {
		Transaction t = new Transaction();
		return t;	
	}

}
