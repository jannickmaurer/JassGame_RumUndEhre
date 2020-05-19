package jass.message;


import java.util.logging.Logger;

import jass.client.message.result.Result;
import jass.client.message.result.ResultCreateAccount;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;

public class CreateAccount extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String username;
	private String password;
	private final int MINUNLENGTH = 1; //TBD
	private final int MINPWLENGTH = 1; //TBD

	public CreateAccount(String[] content) {
		super(content);
		this.username = content[1];
		this.password = content[2];
	}

	//Server checks for username and password, then opens a new account and adds it to the list of accounts
	public void process(Client client) {
		boolean result = false;
		if(username != null && Account.getAccount(username) == null && username.length() >= MINUNLENGTH) {
			if(password != null && password.length() >= MINPWLENGTH) {
				Account newAccount = new Account(username, password);
				Account.add(newAccount);
				result = true;
			}
		}
		client.send(new ResultCreateAccount(result));
	}
}
