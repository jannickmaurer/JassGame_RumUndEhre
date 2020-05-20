package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultDeletePlayroom;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Chatroom;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class DeletePlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String token;
	private String name;

	public DeletePlayroom(String[] content) {
		super(content);
		this.token = content[1];	
		this.name = content[2];
	}
	
	public void process(Client client) {
		boolean result = false;
		if(this.token.equals(client.getToken()) && Playroom.getPlayroom(name) != null && 
				Playroom.getPlayroom(name).getOwner().equals(client.getName())) {
			Chatroom.remove(Playroom.getPlayroom(name).getChatroom());
			Playroom.remove(Playroom.getPlayroom(name));
			result = true;
		}
		client.send(new ResultDeletePlayroom(result));
	}
	


}