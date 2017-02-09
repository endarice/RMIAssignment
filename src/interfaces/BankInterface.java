package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import exceptions.InvalidSession;
import exceptions.InvalidLogin;
import exceptions.InvalidFunds;

import server.Statement;

public interface BankInterface extends Remote {

		public void login(String username, String password) throws RemoteException, InvalidLogin;

		public void deposit(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession;

		public void withdraw(int accountnum, int amount, long sessionID) throws RemoteException, InvalidSession, InvalidFunds;

		public double inquiry(int accountnum, long sessionID) throws RemoteException, InvalidSession;

		public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession;
}

