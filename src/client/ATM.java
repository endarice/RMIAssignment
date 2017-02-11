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
	
	public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
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
        		} catch (InvalidLogin InvalidLogin) {
        			System.err.println(InvalidLogin.getMessage());
        		} catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		}
        		break;
        	case "deposit" :
        		try {
        			bank.deposit(account, amount, sessionID);
        		} catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		} catch (InvalidSession InvalidSession) {
        			System.err.println(InvalidSession.getMessage());
        		}
        		break;
        	case "withdraw" :
        		try {
        			bank.withdraw(account, amount, sessionID);
        		} catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		} catch (InvalidFunds InvalidFunds) {
        			System.err.println(InvalidFunds.getMessage());
        		} catch (InvalidSession InvalidSession) {
        			System.err.println(InvalidSession.getMessage());
        		} catch (InvalidAccount InvalidAccount) {
        			System.err.println(InvalidAccount.getMessage());
        		}       		
        		break;
        	case "inquiry" :
        		try {
        			bank.inquiry(account, sessionID);
        		} catch (RemoteException RemoteException) {
        			System.err.println(RemoteException.getMessage());
        		} catch (InvalidSession InvalidSession) {
        			System.err.println(InvalidSession.getMessage());
        		}
        		break;
        	case "statement" :
        		try {
        			bank.getStatement(account, from, to, sessionID);
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
	    	case "deposit" :
	    		account = Integer.parseInt(args[3]);
	    		amount = Double.parseDouble(args[4]);
	    	case "withdraw" :
	    		account = Integer.parseInt(args[3]);
	    		amount = Double.parseDouble(args[4]);
	    	case "inquiry" :
	    		account = Integer.parseInt(args[3]);
	    	case "statement" :
	    		from = new Date(args[3]);
	    		from = new Date(args[4]);
		} 
    }
}
