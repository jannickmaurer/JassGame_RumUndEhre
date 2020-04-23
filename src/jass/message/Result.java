package jass.message;

import jass.client.model.JassClientModel;
import jass.server.Client;

public class Result extends Message {

	public Result(boolean result) {
		super(new String[] {"Result", Boolean.toString(result)});		
	}

	@Override
	public void process(Client client) {
		
	}

	
	
}
