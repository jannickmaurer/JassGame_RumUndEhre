package jass.server;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;

public class Account implements Serializable {

	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger(); 
	
	private static final ArrayList<Account> accounts = new ArrayList<>();	
	
	private final String username;
	private final String password;
	private String token;
	private int points;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		logger.info("Account created: " + this.toString());
	}
	
	public static void add(Account ac) {
		synchronized(accounts) {
			accounts.add(ac);
		}
	}
	
	public static Boolean exists(String username) {
		synchronized (accounts) {
			for (Account account : accounts) {
				if (account.username.equals(username)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public String toString() {
		return "Username :" + username + " Password: " + password;	
	}
	
	// source: https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
	public String getToken() {
	String uuid = UUID.randomUUID().toString();
	System.out.println(uuid);
    return "uuid = " + uuid;
	}
	
	public static Account getAccount(String username) {
		synchronized (accounts) {
			for (Account account : accounts) {
				if (account.username.equals(username)) {
					return account;
				}
			}
		}
		return null;
	}
	
	public static Boolean checkLogin(String username, String password) {
		synchronized (accounts) {
			for (Account account : accounts) {
				if (account.username.equals(username)) {
					if(account.password.equals(password)) {
						return true;
					}
				}
			}
		}	
		return false;	
	}
	
	
}
