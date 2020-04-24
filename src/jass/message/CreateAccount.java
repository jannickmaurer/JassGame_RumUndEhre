package jass.message;

import jass.server.Account;
import jass.server.Client;

public class CreateAccount extends Message {
	
	private String username;
	private String password;
	private final int MINUNLENGTH = 4;
	private final int MINPWLENGTH = 5;

	public CreateAccount(String[] content) {
		super(content);
		this.username = content[1];
		this.password = content[2];
	}

	public void process(Client client) {
		boolean result = false;
		if(username != null && username.length() >= MINUNLENGTH) {
			if(password != null && password.length() >= MINPWLENGTH) {
				Account newAccount = new Account(username, password);
				Account.add(newAccount);
				result = true;
			}
		}
		client.send(new Result(result));
	}
}
