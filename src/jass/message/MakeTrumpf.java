package jass.message;

import jass.server.Client;
import jass.server.Playroom;

public class MakeTrumpf extends Message{
	private String trumpf;
	
	public MakeTrumpf(String[] content) {
		super(content);
		this.trumpf = trumpf;
	}

	@Override
	public void process(Client client) {
		Playroom.endGame();	
	}
	
	

}
