package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/*This is the MeritBank part of the assignment.
 * This interacts with CDOffering.java and AccountHolder.java
 * Created by Robert Johns
 */
public class MeritBank {

	// All private variables needed in the program
	private static int numOfAccountHolder = 0;
	private static long nextAccountNumber = 1;
	private static AccountHolder[] accountHolderArray = new AccountHolder[0];
	private static CDOffering[] cdOffering = new CDOffering[5];
	private static double totalValue = 0;
	private static FraudQueue fraudQueue = new FraudQueue();

	// This adds another AccountHolder to the array
	static void addAccountHolder(AccountHolder accountHolder) {
		AccountHolder[] tempAccHolder = new AccountHolder[accountHolderArray.length + 1];
		for (int i = 0; i < accountHolderArray.length; i++) {
			tempAccHolder[i] = accountHolderArray[i];
		}
		tempAccHolder[numOfAccountHolder] = accountHolder;
		accountHolderArray = tempAccHolder;
		numOfAccountHolder++;
	}

	// getter for the AccountHolder array
	static AccountHolder[] getAccountHolders() {
		return accountHolderArray;
	}

	// getter for the CDOffering array
	static CDOffering[] getCDOfferings() {
		return cdOffering;
	}

	// compares the best value for CDAccount by its offerings and outputs that
	// offering
	static CDOffering getBestCDOffering(double depositAmount) {
		CDOffering temp = cdOffering[0];
		for (int x = 1; x < cdOffering.length; x++) {
			if (futureValue(depositAmount, cdOffering[x - 1].getInterestRate(),
					cdOffering[x - 1].getTerm()) < futureValue(depositAmount, cdOffering[x].getInterestRate(),
							cdOffering[x].getTerm())) {
				temp = cdOffering[x];
			}
		}
		return temp;
	}

	// compares the second best value for CDAccount by its offerings and outputs
	// that offering
	static CDOffering getSecondBestCDOffering(double depositAmount) {
		CDOffering temp = cdOffering[0];
		CDOffering temp2 = cdOffering[0];
		for (int x = 1; x < cdOffering.length; x++) {
			if (futureValue(depositAmount, cdOffering[x - 1].getInterestRate(),
					cdOffering[x - 1].getTerm()) < futureValue(depositAmount, cdOffering[x].getInterestRate(),
							cdOffering[x].getTerm())) {
				temp2 = temp;
				temp = cdOffering[x];
			}
		}
		return temp2;
	}

	// clears the offering array
	static void clearCDOfferings() {
		cdOffering = null;
	}

	// setter for the offering array
	static void setCDOfferings(CDOffering[] offerings) {
		cdOffering = offerings;
	}

	// getter for a new Account Number
	static long getNextAccountNumber() {
		return nextAccountNumber++;
	}

	// returns the total balance of the whole account holder array
	static double totalBalances() {
		for (AccountHolder x : accountHolderArray) {
			totalValue += x.getCombinedBalance();
		}
		return totalValue;
	}

	// returns the future value of what the user wants based off of parameters
	static double futureValue(double amount, double interestRate, int term) {
		return recursiveFutureValue(amount, term, interestRate);
	}

	public static boolean readFromFile(String fileName) {
		CDOffering offering[] = new CDOffering[0];
		Set<String> allTransactions = new HashSet<String>();
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
			Long nextAccountNumber = Long.valueOf(bufferedReader.readLine());
			int holdOfferNum = Integer.valueOf(bufferedReader.readLine());
			

			for (int i = 0; i < holdOfferNum; i++) {
				offering = Arrays.copyOf(offering, offering.length + 1);
				offering[offering.length - 1] = CDOffering.readFromString(bufferedReader.readLine());
			}

			int numOfAcctHld = Integer.valueOf(bufferedReader.readLine());
			AccountHolder[] newAccountHolders = new AccountHolder[numOfAcctHld];
			for (int i = 0; i < numOfAcctHld; i++) {

				AccountHolder acctH = AccountHolder.readFromString(bufferedReader.readLine());
				int numOfChecking = Integer.valueOf(bufferedReader.readLine());
				for (int j = 0; j < numOfChecking; j++) {
					acctH.addCheckingAccount(CheckingAccount.readFromString(bufferedReader.readLine()));
					
					int numOfTransactions = Integer.valueOf(bufferedReader.readLine());
					for (int a = 0; a < numOfTransactions; a++) {
						allTransactions.add(bufferedReader.readLine());
					}
				}
				
				/*Gets the number of savings and runs a for loop based on that value.
				 * Sends those values to the savings account class to be read into
				 * for those values.
				 * Also does the same thing for the number of transactions.
				 */
				int numOfSavings = Integer.valueOf(bufferedReader.readLine());
				for (int k = 0; k < numOfSavings; k++) {
					acctH.addSavingsAccount(SavingsAccount.readFromString(bufferedReader.readLine()));
				
					int numOfTransactions = Integer.valueOf(bufferedReader.readLine());
					for (int b = 0; b < numOfTransactions; b++) {
						allTransactions.add(bufferedReader.readLine());
					}
				}

				

				int numOfCD = Integer.valueOf(bufferedReader.readLine());
				for (int m = 0; m < numOfCD; m++) {
					acctH.addCDAccount(CDAccount.readFromString(bufferedReader.readLine()));
				
					int numOfTransactions = Integer.valueOf(bufferedReader.readLine());
					for (int b = 0; b < numOfTransactions; b++) {
						allTransactions.add(bufferedReader.readLine());
					}
				}

				newAccountHolders[i] = acctH;
			}
			setNextAccountNumber(nextAccountNumber);
			cdOffering = offering;
			accountHolderArray = newAccountHolders;
			int numberOfFraudQueueTransactions = Integer.valueOf(bufferedReader.readLine());
			for(int fqt = 0; fqt < numberOfFraudQueueTransactions; fqt++) {
				fraudQueue.addTransaction(Transaction.readFromString(bufferedReader.readLine()));
			}
			reader.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ExceedsCombinedBalanceLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		

	}

	private static void setNextAccountNumber(Long nextAccountNumber2) {
		nextAccountNumber = nextAccountNumber2;
	}

	static AccountHolder[] sortAccountHolders() {
		Arrays.sort(accountHolderArray);
		for (AccountHolder a : accountHolderArray) {
			System.out.println(a.getCDBalance());
		}
		return accountHolderArray;
	}

	static boolean writeToFile(String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(String.valueOf(nextAccountNumber));
			bufferedWriter.newLine();
			bufferedWriter.write(String.valueOf(cdOffering.length));
			bufferedWriter.newLine();
			for (int i = 0; i < cdOffering.length; i++) {
				bufferedWriter.write(cdOffering[i].writeToString());
				bufferedWriter.newLine();
			}

			bufferedWriter.write(String.valueOf(accountHolderArray.length));
			bufferedWriter.newLine();
			for (int j = 0; j < accountHolderArray.length; j++) {
				bufferedWriter.write(accountHolderArray[j].writeToString());
				bufferedWriter.newLine();
				bufferedWriter.write(String.valueOf(accountHolderArray[j].getCheckingAccounts().length));
				bufferedWriter.newLine();

				for (int k = 0; k < accountHolderArray[j].getCheckingAccounts().length; k++) {
					bufferedWriter.write(accountHolderArray[j].getCheckingAccounts()[k].writeToString());
					bufferedWriter.newLine();
				}

				bufferedWriter.write(String.valueOf(accountHolderArray[j].getSavingsAccounts().length));
				bufferedWriter.newLine();
				for (int m = 0; m < accountHolderArray[j].getSavingsAccounts().length; m++) {
					bufferedWriter.write(accountHolderArray[j].getSavingsAccounts()[m].writeToString());
					bufferedWriter.newLine();
				}

				bufferedWriter.write(String.valueOf(accountHolderArray[j].getCDAccounts().length));
				bufferedWriter.newLine();
				for (int n = 0; n < accountHolderArray[j].getCDAccounts().length; n++) {
					bufferedWriter.write(accountHolderArray[j].getCDAccounts()[n].writeToString());
					bufferedWriter.newLine();
				}

			}
			writer.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		if (years > 0) {
			//double baseAmount = recursiveFutureValue(amount, years - 1, interestRate);
			double newAmount = amount + (amount * interestRate);
			//return newAmount;
			return recursiveFutureValue(newAmount, years - 1, interestRate);
		}
		return amount;

	}

	public static boolean processTransaction(Transaction transaction)
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		// where do I ExceedsFraudSuspicionLimitException
		transaction.process();
		/*
		BankAccount tA = transaction.getTargetAccount();
		BankAccount sA = transaction.getSourceAccount();
		if (sA == null) {
			if (transaction instanceof WithdrawTransaction) {
				if (transaction.getAmount() < -1000) {
					getFraudQueue().addTransaction(transaction);
					throw new ExceedsFraudSuspicionLimitException();
				} else if (transaction.getAmount() > 0) {
					throw new NegativeAmountException();
				} else if (transaction.getAmount() + tA.getBalance() < 0) {
					throw new ExceedsAvailableBalanceException();
				}
				return true;
			}
			if (transaction.getAmount() > 1000) {
				getFraudQueue().addTransaction(transaction);
				throw new ExceedsFraudSuspicionLimitException();
			}
			if (transaction.getAmount() < 0) {
				throw new NegativeAmountException();
			}
			return true;
		} else {
			if (transaction.getAmount() > 1000) {
				getFraudQueue().addTransaction(transaction);
				throw new ExceedsFraudSuspicionLimitException();
			}
			if (transaction.getAmount() < 0) {
				throw new NegativeAmountException();
			}
			if (transaction.getAmount() > transaction.getSourceAccount().getBalance()) {
				throw new ExceedsAvailableBalanceException();

			} else {
				sA.withdraw(transaction.amount);
				tA.deposit(transaction.amount);
			}		
		}
		*/
		return true;
	}

	public static FraudQueue getFraudQueue() {
		return fraudQueue;

	}

	public static BankAccount getBankAccount(long accountId) {
		for (AccountHolder accountHolder : accountHolderArray) {
			for (BankAccount bankAccount : accountHolder.getSavingsAccounts()) {
				if (bankAccount.getAccountNumber() == accountId) {
					return bankAccount;
				}
			}
			for (BankAccount bankAccount : accountHolder.getCheckingAccounts()) {
				if (bankAccount.getAccountNumber() == accountId) {
					return bankAccount;
				}
			}
			for (BankAccount bankAccount : accountHolder.getCDAccounts()) {
				if (bankAccount.getAccountNumber() == accountId) {
					return bankAccount;
				}
			}

		}
		return null;
	}
}
