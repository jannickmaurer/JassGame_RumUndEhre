package jass.message;

import jass.server.Client;

public class Ping extends Message {
	
	public Ping(String[] content) {
		super(content);
		
	}

	@Override
	public void process(Client client) {
		client.send(new Result(true));
	}
	
}
