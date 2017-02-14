package server;

import java.util.Date;
import java.util.List;

public class Account {

	private String username;
	private String password;
	
	private double balance;
	
	private static int accountNum = 1378;

	private Date startDate;
	private Date endDate;

	private List<Transaction> transactions;
	
	public Account(String username, String password) {

		this.setUsername(username);
		this.setPassword(password);
		
		this.setBalance(0);
		this.accountNum ++;

		this.startDate = new Date();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction t){
		transactions.add(t);
	}

}
