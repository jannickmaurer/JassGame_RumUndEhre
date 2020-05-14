package jass.message;

import java.util.logging.Logger;

import jass.commons.ServiceLocator;
import jass.server.Client;
import jass.server.Playroom;
import jass.server.TrumpfGame;

public class CreatePlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String token;
	private String name;
	private String playmode;
	
	public CreatePlayroom(String[] content) {
		super(content);
		this.token = content[1];
		this.name = content[2];
		this.playmode = content[3];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		if(this.token.equals(client.getToken())) {
			if(this.playmode.equals("Trumpf")) {
				Playroom playroom = new TrumpfGame(this.name, client);
				Playroom.add(playroom);
				result = true;
			}
			client.send(new Result(result));
		}
	}
}
