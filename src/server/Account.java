package server;

public class Account {
	private String username;
	private String password;
	
	private double balance;
	
	private int accountNum = 13463878;
	
	public Account(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		
		this.setBalance(0);
		this.accountNum ++;
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
}
