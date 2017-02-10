package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import exceptions.*;

import server.Account;
import server.Statement;

public interface BankInterface extends Remote {

		public long login(String username, String password) throws RemoteException, InvalidLogin;

		public void deposit(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession;

		public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession, InvalidFunds, InvalidAccount;

		public double inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession;

		public Statement getStatement(Account acc, Date from, Date to, long sessionID) throws RemoteException, InvalidSession;
}

