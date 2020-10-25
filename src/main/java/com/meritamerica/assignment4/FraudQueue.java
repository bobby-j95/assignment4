package com.meritamerica.assignment4;
import java.util.ArrayList;

public class FraudQueue {
	
	private static ArrayList<Transaction> queue; // this should not be static 
	
	public FraudQueue() {
		
	}
	
	public static void addTransaction(Transaction transaction) { // this should not be static 
		queue.add(transaction);
	}
	public Transaction getTransaction() {
		Transaction t;
		return null;	
	}

}
