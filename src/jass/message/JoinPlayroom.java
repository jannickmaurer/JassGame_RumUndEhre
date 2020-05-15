package jass.message;

import java.util.logging.Logger;

import jass.commons.ServiceLocator;
import jass.message.result.ResultJoinPlayroom;
import jass.server.Client;
import jass.server.Playroom;

public class JoinPlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String name;
	

	public JoinPlayroom(String[] content) {
		super(content);
		this.token = content[1];
		this.name = content[2];
	}
	
	@Override
	public void process(Client client) {
		Boolean result = false;
		if(this.token.equals(client.getToken())) {
			Playroom playroom = Playroom.getPlayroom(this.name);
			if(playroom != null) {
				playroom.addMember(client.getName());
				result = true;
			}
		}
		client.send(new ResultJoinPlayroom(result));
	}
}


