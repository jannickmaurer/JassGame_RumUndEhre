package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultDeleteAccount;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;
import jass.server.Playroom;

public class DeleteAccount extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String token;

	public DeleteAccount(String[] content) {
		super(content);
		this.token = content[1];	
	}
	
	public void process(Client client) {
		boolean result = false;
		if(this.token.equals(client.getToken())) {
			Playroom.removeMemberFromAny(client.getName());
			Account.remove(client.getAccount());
			client.setAccount(null);
			client.setToken(null);
			result = true;
		}
		client.send(new ResultDeleteAccount(result));
		
	}
	


}
