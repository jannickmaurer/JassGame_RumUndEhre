package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultBroadcastEndGame;
import jass.client.message.result.ResultBroadcastStartGame;
import jass.client.message.result.ResultEndGame;
import jass.client.message.result.ResultLogin;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;

public class EndGame extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;

	public EndGame(String[] content) {
		super(content);	
		this.token = content[1];
		
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		if(this.token.equals(client.getToken())) {
			client.getPlayroom().endGame();
			String[] content = new String[] {"ResultBroadcastEndGame"};
			client.getPlayroom().send(new ResultBroadcastEndGame(content));
			result = true;
		}
		client.send(new ResultEndGame(result));
	}

}
