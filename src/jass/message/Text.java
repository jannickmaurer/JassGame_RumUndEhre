package jass.message;

import jass.server.Client;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class Text extends Message {
	private String name;
	private String message;
	
	public Text(String [] content) {
		super(content);
		
	}
	
	/**
	 * This message type does no processing at all
	 */
	@Override
	public void process(Client client) {
		
		
		
		
		
		
	}
	

}
