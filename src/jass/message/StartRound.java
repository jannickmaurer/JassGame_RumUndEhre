package jass.message;

import java.util.logging.Logger;

import jass.client.message.result.ResultPing;
import jass.client.message.result.ResultBroadcastStartGame;
import jass.client.message.result.ResultBroadcastStartRound;
import jass.client.message.result.ResultLogin;
import jass.client.message.result.ResultShuffle;
import jass.client.message.result.ResultStartGame;
import jass.client.message.result.ResultStartRound;
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
public class StartRound extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String gameType;
	private String additionalInfo;

	public StartRound(String[] content) {
		super(content);	
		this.token = content[1];
		this.gameType = content[2];
		if(content.length > 3) this.additionalInfo = content[3];
	}

	@Override
	public void process(Client client) {
		boolean result = false;

		if(this.token.equals(client.getToken())) {
			
			result = true;
			client.send(new ResultStartRound(result));

			Playroom playroom = client.getPlayroom();
			playroom.setGameType(gameType);
			if(gameType.equals("Trumpf")) playroom.setTrumpf(additionalInfo);
			String[] content = new String[] {"ResultBroadcastStartRound", gameType, additionalInfo};
			playroom.send(new ResultBroadcastStartRound(content));
//			CardCreation cc = new CardCreation();
//			for(String s : playroom.getMembers()) {
//				System.out.println(s);
//				String tableCardsAsString = cc.shuffledPlayerCards();
//				String[] content2 = new String[] {"ResultShuffle", tableCardsAsString};
//				Message msg = new ResultShuffle(content2);
//				Client.getClient(s).send(msg);
//				Client.getClient(s).getAccount().setCurrentPlayerCards(tableCardsAsString);
//			}
			result = true;
		} else {
			client.send(new ResultStartRound(result));
		}
	}
}

