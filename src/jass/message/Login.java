package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultLogin;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;
/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
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
			String[] content = new String[] {"ResultLogin", Boolean.toString(result), token, this.username};
			client.send(new ResultLogin(content));
		} else {
			client.send(new ResultLogin(result));
		}
	}

}
