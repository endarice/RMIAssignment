package client;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.BankInterface;

public class ATM {
	public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Bank";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            BankInterface bank = (BankInterface) registry.lookup(name);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
	}
}