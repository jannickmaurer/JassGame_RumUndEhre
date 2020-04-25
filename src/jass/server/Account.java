package jass.server;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;

public class Account implements Serializable {

	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger(); 
	
	private static final ArrayList<Account> accounts = new ArrayList<>();	
	
	private final String username;
	private final String password;
//	private String token;
	private int points;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public static void add(Account ac) {
		synchronized(accounts) {
			accounts.add(ac);
		}
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	
	
	
	
}
