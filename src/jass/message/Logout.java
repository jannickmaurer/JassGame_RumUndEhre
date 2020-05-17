package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultDeleteAccount;
import jass.client.message.result.ResultLogout;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;
import jass.server.Playroom;

public class Logout extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String token;

	public Logout(String[] content) {
		super(content);
		this.token = content[1];	
	}
	
	public void process(Client client) {
		boolean result = false;
		if(this.token.equals(client.getToken())) {
			Playroom playroom = client.getPlayroom();
			if(playroom != null) playroom.removeMember(client.getName());
			client.setToken(null); 
			client.setAccount(null); 
			result = true;
		}
		client.send(new ResultLogout(result));
	}
}
	



