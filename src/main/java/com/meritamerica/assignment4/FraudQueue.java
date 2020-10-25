package com.meritamerica.assignment4;
import java.util.ArrayList;

public class FraudQueue {
	
	private static ArrayList<Transaction> queue = new ArrayList<Transaction>();
	
	public FraudQueue() {
		
	}
	
	public static void addTransaction(Transaction transaction) {
		queue.add(transaction);
	}
	public Transaction getTransaction() {
		Transaction t = queue.get(0);
		queue.remove(t);
		return t;	
	}

}
