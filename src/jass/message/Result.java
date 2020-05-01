package jass.message;

import jass.client.model.JassClientModel;
import jass.server.Client;

public class Result extends Message {
	String token;

	public Result(boolean result) {
		super(new String[] {"Result", Boolean.toString(result)});		
	}

	public Result(String[] content) {
		super(content);
		this.token = content[1];
	}
	
	@Override
	public void process(Client client) {
		
	}

	
	
}
