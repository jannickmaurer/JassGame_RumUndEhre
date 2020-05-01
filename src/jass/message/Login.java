package jass.message;

import java.util.logging.Logger;

import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;

public class Login extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String username;
	private String password;

	public Login(String[] content) {
		super(content);
		this.username = content[1];
		this.password = content[2];		
	}

	@Override
	public void process(Client client) {
		Account account;
		String token;
		Boolean result = false;
		if(Account.checkLogin(username, password) == true) {
			account = Account.getAccount(username);
			client.setAccount(account);
			token = account.getToken();
			client.setToken(token);
			result = true;
			String[] content = new String[] {"Result", Boolean.toString(result), token};
			client.send(new Result(content));
		} else {
			client.send(new Result(result));
		}
	}

}
