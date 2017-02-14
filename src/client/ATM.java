package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;

import exceptions.InvalidAccount;
import exceptions.InvalidFunds;
import exceptions.InvalidLogin;
import exceptions.InvalidSession;
import interfaces.BankInterface;

public class ATM {
	static String server_address, operation;
	static BankInterface bank;
	static String username, password;
	static int account;
	static double amount;
	static long sessionID;
	static Date from, to;
    static double balance;

	
	public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setProperty("java.security.policy", "file:/Users/DJ/projects/RMIAssignment/src/all.policy");
            System.setSecurityManager(new SecurityManager());
        }
        try {
        	getArgs(args);
            String name = "Bank";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            bank = (BankInterface) registry.lookup(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch(operation) {
        	case "login" :
        		try {
        			bank.login(username, password);
                    System.out.println("\nLogged in " + username);
                } catch (InvalidLogin InvalidLogin) {
        			System.err.println(InvalidLogin.getMessage());
        		} catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		}
        		break;
        	case "deposit" :
        		try {
        		    balance = bank.deposit(account, amount, sessionID);
                    System.out.println("\nDeposit Success");
                    System.out.println("Balance is " + balance);
                } catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		} catch (InvalidSession InvalidSession) {
                    System.err.println(InvalidSession.getMessage());
        		} catch (InvalidAccount invalidAccount) {
                    invalidAccount.printStackTrace();
                }
                break;
        	case "withdraw" :
        		try {
        			balance = bank.withdraw(account, amount, sessionID);
                    System.out.println("\nWithdraw Success");
                    System.out.println("Balance is " + balance);
                } catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		} catch (InvalidFunds InvalidFunds) {
        			System.err.println(InvalidFunds.getMessage());
        		} catch (InvalidSession InvalidSession) {
        			System.err.println("YOYOYOYOYO");
        		} catch (InvalidAccount InvalidAccount) {
        			System.err.println(InvalidAccount.getMessage());
        		}       		
        		break;
        	case "inquiry" :
        		try {
        			balance = bank.inquiry(account, sessionID);
                    System.out.println("\nInquiry Success");
                    System.out.println("Balance is " + balance);
                } catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		} catch (InvalidSession InvalidSession) {
        			System.err.println(InvalidSession.getMessage());
        		}
        		break;
        	case "statement" :
        		try {
                    System.out.println(bank.getStatement(account, from, to, sessionID));
                    System.out.println("\nStatement Success");
                } catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		} catch (InvalidSession InvalidSession) {
        			System.err.println(InvalidSession.getMessage());
        		}
        		break;
        }      
	}
	
	public static void getArgs(String args[]) {
		operation = args[2];
		switch(operation) {
	    	case "login" :
	    		username = args[3];
	    		password = args[4];
                break;
	    	case "deposit" :
                account = Integer.parseInt(args[3]);
                sessionID = Integer.parseInt(args[4]);
                amount = Double.parseDouble(args[5]);
                break;
            case "withdraw":
	    		account = Integer.parseInt(args[3]);
                sessionID = Integer.parseInt(args[4]);
	    		amount = Double.parseDouble(args[5]);
                break;
	    	case "inquiry" :
	    		account = Integer.parseInt(args[3]);
                sessionID = Integer.parseInt(args[4]);
                break;
	    	case "statement" :
	    		account =  Integer.parseInt(args[3]);
	    		from = new Date(args[4]);
	    		from = new Date(args[5]);
                break;
		} 
    }
}
