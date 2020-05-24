package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultBroadcastListMembers;
import jass.client.message.result.ResultJoinPlayroom;
import jass.client.message.result.ResultListMembers;
import jass.client.message.result.ResultListPlayrooms;
import jass.commons.ServiceLocator;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
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
				client.setPlayroom(playroom);
				result = true;
				String[] content = new String[] {"ResultJoinPlayroom", Boolean.toString(result), playroom.getOwner(), playroom.getName(), playroom.getGameType()};
				client.send(new ResultJoinPlayroom(content));
				String[] temp = new String[] {"ResultBroadcastListMembers"};
				String[] content2 = this.combineArrayAndArrayList(temp, playroom.getMembers());
				playroom.send(new ResultBroadcastListMembers(content2));
			} 
		} else {
			client.send(new ResultJoinPlayroom(result));

		}
	}
}



