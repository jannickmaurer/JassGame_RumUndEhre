package jass.message;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultListPlayrooms;
import jass.commons.ServiceLocator;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class ListPlayrooms extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;

	public ListPlayrooms(String[] content) {
		super(content);
		this.token = content[1];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		
		if(this.token.equals(client.getToken())) {
			result = true;
			String[] temp = new String[] {"ResultListPlayrooms", Boolean.toString(result)};
			String[] content = this.combineArrayAndArrayList(temp, Playroom.getPlayroomNames());
			client.send(new ResultListPlayrooms(content));
			
		} else {
			client.send(new ResultListPlayrooms(result));
		}
	}
}

