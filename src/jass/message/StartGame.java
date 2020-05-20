package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultBroadcastStartGame;
import jass.client.message.result.ResultLogin;
import jass.client.message.result.ResultShuffle;
import jass.client.message.result.ResultStartGame;
import jass.commons.ServiceLocator;
import jass.server.Account;
import jass.server.CardCreation;
import jass.server.Client;
import jass.server.Playroom;

/*
 * Class developed by Jannick
 * Individual message client -> server
 * Triggers events in client
 */
public class StartGame extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String maxPoints;

	public StartGame(String[] content) {
		super(content);	
		this.token = content[1];
		this.maxPoints = content[2];
	}

	@Override
	public void process(Client client) {
		boolean result = false;
		client.getPlayroom().setMaxPoints(Integer.parseInt(maxPoints));
		Playroom playroom = client.getPlayroom();
		if(this.token.equals(client.getToken())) {
			if(client.getPlayroom().getOwner().equals(client.getName())) {
				client.getPlayroom().startGame();
				String[] content = new String[] {"ResultBroadcastStartGame", Integer.toString(client.getPlayroom().getMaxPoints())};
				client.getPlayroom().send(new ResultBroadcastStartGame(content));
				CardCreation cc = new CardCreation();
				for(String s : playroom.getMembers()) {
					System.out.println(s);
					String tableCardsAsString = cc.shuffledPlayerCards();
					String[] content2 = new String[] {"ResultShuffle", tableCardsAsString};
					Message msg = new ResultShuffle(content2);
					Client.getClient(s).send(msg);
					Client.getClient(s).getAccount().setCurrentPlayerCards(tableCardsAsString);
				}
				result = true;
			}
		}
		client.send(new ResultStartGame(result));
	}
	
	
}
