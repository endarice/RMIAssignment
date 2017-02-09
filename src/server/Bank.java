package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.Date;
import java.util.List;

import interfaces.BankInterface;

import exceptions.InvalidSession;
import exceptions.InvalidLogin;
import exceptions.InvalidAccount;
import exceptions.InvalidFunds;

public class Bank extends UnicastRemoteObject implements BankInterface {
	private List<Account> accounts; // users accounts
	public Bank() throws RemoteException {
		Account user1 = new Account("user1", "pass1");
		Account user2 = new Account("user2", "pass2");
		Account user3 = new Account("user3", "pass3");
		accounts.add(user1);
		accounts.add(user2);
		accounts.add(user3);
	}
	
	public static void main(String args[]) throws Exception {	
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Bank";
            BankInterface bank = new Bank();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, bank);
            System.out.println("Bank bound");
        } catch (Exception e) {
            System.err.println("Bank exception:");
            e.printStackTrace();
        }
	}
	
	public void login(String username, String password) throws RemoteException, InvalidLogin {
		for(Account a : accounts) {
			if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
				System.out.println("Successful Login");
			} else {
				throw new InvalidLogin("msg");
			}
		}
	}
		
	public void deposit(int account, int amount, long sessionID) throws RemoteException, InvalidSession {
		try {
			Account a = getAccount(account, sessionID);
			double balance = a.getBalance();
			a.setBalance(balance + amount);
		} catch (Exception InvalidAccount) {
			System.err.println(InvalidAccount.getMessage());
		}
	}
	
	public void withdraw(int account, int amount, long sessionID) throws RemoteException, InvalidSession, InvalidFunds {
		try {
			Account a = getAccount(account, sessionID);
			double balance = a.getBalance();
			if (amount > balance) {
				throw new InvalidFunds("msg");
			} else {
				a.setBalance(balance = amount);
			}
		} catch (Exception InvalidAccount) {
			System.err.println(InvalidAccount.getMessage());
		} 		
	}
	
	public double inquiry(int account, long sessionID) throws RemoteException, InvalidSession {	
		double balance = 0;
		try {
			Account a = getAccount(account, sessionID);
			double bal = a.getBalance();
			balance = bal;
		} catch (Exception InvalidAccount) {
			System.err.println(InvalidAccount.getMessage());
		} 
		return balance;
	}
	
	public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {	
			Statement s = new Statement();
			return s;
	}
	
	public Account getAccount(int account, long sessionID) throws RemoteException, InvalidAccount {
		for(Account  a : accounts) {
			if(a.getAccountNum() == account) {
				return a;
			}
		}
		throw new InvalidAccount("msg");
	}
}