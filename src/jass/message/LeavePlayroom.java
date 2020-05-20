package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultJoinPlayroom;
import jass.client.message.result.ResultLeavePlayroom;
import jass.commons.ServiceLocator;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class LeavePlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	

	public LeavePlayroom(String[] content) {
		super(content);
		this.token = content[1];
	}
	
	@Override
	public void process(Client client) {
		Boolean result = false;
		if(this.token.equals(client.getToken())) {
			Playroom playroom = client.getPlayroom();
			if(playroom != null) {
				playroom.removeMember(client.getName());
				client.setPlayroom(null);
				result = true;
			}
		}
		client.send(new ResultLeavePlayroom(result));
	}
}



