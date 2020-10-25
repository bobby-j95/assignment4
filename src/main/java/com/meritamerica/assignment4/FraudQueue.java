package com.meritamerica.assignment4;
import java.util.ArrayList;

public class FraudQueue {
	
	private static ArrayList<Transaction> queue;
	
	public FraudQueue() {
		
	}
	
	public static void addTransaction(Transaction transaction) {
		queue.add(transaction);
	}
	public Transaction getTransaction() {
		Transaction t;
		return null;	
	}

}
