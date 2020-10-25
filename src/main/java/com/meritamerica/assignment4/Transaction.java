package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction {

	protected BankAccount sourceAccount;
	protected BankAccount targetAccount;
	protected double amount;
	protected String reason;
	protected Date openDate;
	protected boolean isProcessed;

	public BankAccount getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public BankAccount getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public java.util.Date getTransactionDate() {
		return openDate;
	}

	public void setTransactionDate(java.util.Date date) {
		openDate = date;
	}

	public String writeToString() {
		StringBuilder finalString = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (sourceAccount == null) {
			finalString.append(-1);
		} else {
			finalString.append(sourceAccount.getAccountNumber());
		}
		finalString.append(",");
		finalString.append(targetAccount.getAccountNumber());
		finalString.append(",");
		finalString.append(amount);
		finalString.append(",");
		finalString.append(dateFormat.format(openDate));
		return finalString.toString();
	}

	public static Transaction readFromString(String transactionDataString)
			throws ParseException, NumberFormatException {

		
		try {
			String[] holding = transactionDataString.split(",");
			SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
			Long sourceAccountNum = Long.parseLong(holding[0]);
			Long targetAccountNum = Long.parseLong(holding[1]);
			double balance = Double.parseDouble(holding[2]);
			Date accountOpenedOn = date.parse(holding[3]);
			BankAccount tA = MeritBank.getBankAccount(targetAccountNum);
			BankAccount sA = MeritBank.getBankAccount(sourceAccountNum);
			if (sourceAccountNum < 0) {
				if (balance < 0) {
					WithdrawTransaction wt = new WithdrawTransaction(tA, balance);
					wt.setTransactionDate(accountOpenedOn);
					return wt;
				} else {
					DepositTransaction dt = new DepositTransaction(tA, balance);
					dt.setTransactionDate(accountOpenedOn);
					return dt;
				}
			} else {
				TransferTransaction t = new TransferTransaction(sA, tA, balance);
				t.setTransactionDate(accountOpenedOn);
			}
			return null;

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	public abstract void process()
			throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;

	public boolean isProcessedByFraudTeam() {
		return isProcessed;
	}

	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getRejectionReason() {
		return reason;
	}

	public void setRejectionReason(String reason) {
		this.reason = reason;
	}

}
