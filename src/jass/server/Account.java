package jass.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
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
	private int wins;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		logger.info("Account created: " + this.toString());
	}
	
	//add account into list of accounts
	public static void add(Account ac) {
		synchronized(accounts) {
			accounts.add(ac);
			saveAccounts();
		}
	}
	
	public static void remove(Account account) {
		synchronized (accounts) {
			for (Iterator<Account> i = accounts.iterator(); i.hasNext();) {
				if (account == i.next()) i.remove();
			}
		}
	}
	
	public static void saveAccounts() {
		File accountFile = new File(Server.getDirectory() + "accounts.sav");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(accountFile))) {
			synchronized (accounts) {
				out.writeInt(accounts.size());
				for (Account account : accounts) {
					out.writeObject(account);
				}
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			logger.severe("Unable to save accounts: " + e.getMessage());
		}
	}
	
	public static void readAccounts() {
		File accountFile = new File(Server.getDirectory() + "accounts.sav");
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(accountFile))) {
			int num = in.readInt();
			for (int i = 0; i < num; i++) {
				Account account = (Account) in.readObject();
				accounts.add(account);
				logger.info("Loaded account " + account.getUsername());
			}
		} catch (Exception e) {
			logger.severe("Unable to read accounts: " + e.getMessage());
		}
	}
	
	//check if an account exists depending on username -> 
//	public static Boolean exists(String username) {
//		synchronized (accounts) {
//			for (Account account : accounts) {
//				if (account.username.equals(username)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	//Add playing points to an account
	public void addPoints(int points) {
		this.points += points;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	//Add overall wins to account
	public void addWin(int wins) {
		this.wins += wins;
	}
	
	public String toString() {
		return "Username :" + username + " Password: " + password;	
	}
	
	// source: https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
	public String getToken() {
	String uuid = UUID.randomUUID().toString();
	System.out.println(uuid);
    return uuid;
	}
	
	//return account the client is connectet with
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
	
	//Check if an account exists depending on username and password
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
	
	public String getUsername() {
		return this.username;
	}
	
	
	
}
