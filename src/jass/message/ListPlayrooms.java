package jass.message;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;
import jass.server.Client;
import jass.server.Playroom;

// NOT YET DONE


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
			ArrayList<String> names = new ArrayList<>();
			for(Playroom p : Playroom.getPlayrooms()) {
				names.add(p.getName());
			}
			result = true;
			String[] content = new String[] {"Result", Boolean.toString(result), "ListPlayrooms"};
			client.send(new Result(content, names));
		} else {
			client.send(new Result(result));
		}
	}
}
