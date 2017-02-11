package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Sessions.Shesh;
import exceptions.*;
import interfaces.BankInterface;

public class Bank extends UnicastRemoteObject implements BankInterface {

	private static List<Account> accounts = new ArrayList<Account>(); // users accounts
	private static String withdrawTransaction = "withdraw Transaction";
	private static String depositTransaction = "Deposit Transaction";
    private static List<Shesh> shessions = new ArrayList<>();

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

	public long login(String username, String password) throws RemoteException, InvalidLogin {
		for(Account a : accounts) {
			if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
				System.out.println("Successful Login");
				Shesh userSession =  new Shesh(a);
                shessions.add(userSession);

                return userSession.getSessionID();

			} else {
				throw new InvalidLogin("msg");
			}
		}
		return 0;
	}
		
	public void deposit(int account, double amount, long sessionID) throws RemoteException, InvalidSession {
        if(getShesh(sessionID)){

            try {
                Account a = getAccount(account, sessionID);
                double balance = a.getBalance();
                a.setBalance(balance + amount); //need to add more catches here
                Transaction t = new Transaction(a.getAccountNum(), depositTransaction);
                a.addTransaction(t);
            } catch (Exception InvalidAccount) {
                System.err.println(InvalidAccount.getMessage());
            }

        } throw new InvalidSession("Session has expired, please sign back in");
	}
	
	public void withdraw(int account, double amount, long sessionID) throws RemoteException, InvalidSession, InvalidFunds, InvalidAccount {
        if(getShesh(sessionID)){
            try {
                Account a = getAccount(account, sessionID);
                double balance = a.getBalance();
                if (amount > balance) {
                    throw new InvalidFunds("msg");
                } else {
                    a.setBalance(balance = amount); ////need to add more catches here
                    Transaction t = new Transaction(a.getAccountNum(), withdrawTransaction);
                    a.addTransaction(t);
                }
            } catch (Exception InvalidAccount) {
                System.err.println(InvalidAccount.getMessage());
            }

        } throw new InvalidSession("Session has expired, please sign back in");
	}
	
	public double inquiry(int account, long sessionID) throws RemoteException, InvalidSession {
        if(getShesh(sessionID)) {
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
        throw new InvalidSession("The Sessions has expired, , please sign back in");	
	}
	
	public Statement getStatement(int accountnum, Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
			Statement s = null;
			try {
				Account a = getAccount(accountnum, sessionID);
				s = new Statement(a,from , to);
			} catch (InvalidAccount InvalidAccount) {
				InvalidAccount.printStackTrace();
			}
			return s;
	}

	/* ----------- Non-inherited Methods ------------ */
	
	public Account getAccount(int account, long sessionID) throws RemoteException, InvalidAccount {
		for(Account  a : accounts) {
			if(a.getAccountNum() == account) {
				return a;
			}
		}
		throw new InvalidAccount("msg");
	}

	public boolean getShesh(long sessionID) throws RemoteException {
            for (Shesh shesh : shessions){
                if(shesh.getSessionID() == sessionID){
                    if(shesh.isAlive() == false){
                        return false;
                    }
                    else{
                        shesh.resetTimerCount(0);
                        return true;
                    }
                } // end of first if
            } // end of for loop
           return false;
    }
}