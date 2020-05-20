package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultCreatePlayroom;
import jass.commons.ServiceLocator;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class CreatePlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String token;
	private String name;
	private String gameType;
	private String maxPoints;
	
	public CreatePlayroom(String[] content) {
		super(content);
		this.token = content[1];
		this.name = content[2];
		this.gameType = content[3];
		if(content.length > 4) this.maxPoints = content[4];
		
	}


	@Override
	public void process(Client client) {
		boolean result = false;
		if(this.token.equals(client.getToken()) && Playroom.getPlayroom(name) == null) {
			if(this.gameType.equals("Trumpf")) {
				Playroom playroom = new Playroom(this.name, client.getName(), this.gameType);
				Playroom.add(playroom);
				result = true;
			}
		}
		client.send(new ResultCreatePlayroom(result));
	}  
}
