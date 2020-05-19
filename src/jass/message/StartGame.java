package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.Result;
import jass.client.message.result.ResultBroadcastStartGame;
import jass.client.message.result.ResultLogin;
import jass.client.message.result.ResultShuffle;
import jass.client.message.result.ResultStartGame;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.Client;
import jass.server.Playroom;

public class StartGame extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;

	public StartGame(String[] content) {
		super(content);	
		this.token = content[1];
		
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		Playroom playroom = client.getPlayroom();
		if(this.token.equals(client.getToken())) {
			if(client.getPlayroom().getOwner().equals(client.getName())) {
				client.getPlayroom().startGame();
				String[] content = new String[] {"ResultBroadcastStartGame"};
				client.getPlayroom().send(new ResultBroadcastStartGame(content));
				
				for(String s : playroom.getMembers()) {
					String tableCardsAsString = playroom.getNinePlayerCards();
					String[] content2 = new String[] {"ResultShuffle", tableCardsAsString};
					Message msg = new ResultShuffle(content2);
					Client.getClient(s).send(msg);
					Client.getClient(s).getAccount().setCurrentTableCards(tableCardsAsString);
					
				}
				result = true;
				
			}
		}
		client.send(new ResultStartGame(result));
	}
	

}
