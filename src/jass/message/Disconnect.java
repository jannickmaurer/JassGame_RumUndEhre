package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultDeleteAccount;
import jass.client.message.result.ResultDisconnect;
import jass.client.message.result.ResultLogout;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;
import jass.server.Playroom;

public class Disconnect extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	public Disconnect(String[] content) {
		super(content);
	}
	
	public void process(Client client) {
		boolean result = false;
		
		Playroom playroom = client.getPlayroom();
		if(playroom != null) playroom.removeMember(client.getName());
		client.setToken(null); 
		client.setAccount(null); 
		result = true;
		
		client.send(new ResultDisconnect(result));
	}
}
	



