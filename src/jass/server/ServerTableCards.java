package jass.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.Card;
import jass.commons.Cards;
import jass.commons.ServiceLocator;
import jass.commons.Trumpf;

public class ServerTableCards extends Cards{
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	ArrayList<Card> serverTableCards = new ArrayList<>();
	ArrayList<String> usernames = new ArrayList<>();

	public ServerTableCards() {
		super();
	}

//	public ArrayList<Card> getServerTableCards() {
//		return serverTableCards;
//	}
	
//	public boolean hasServerTableCards() {
//		return serverTableCards.size() != 0; 
//	}

	public boolean isComplete() { // brauchts die hier???
		return serverTableCards.size() == 4;
	}
	
	public int getPoints() {
		logger.info("Getting points for: " + serverTableCards.toString());
		return Trumpf.getPoints(serverTableCards);
	}
	
	public Card getCard(int i) {
		return serverTableCards.get(i);
	}
	
	public void addClientCard(String clientCard) {
		String[] temp = clientCard.split("\\:");
		usernames.add(temp[0]);
		serverTableCards.add(new Card(temp[1]));
	}
	
	public void clearServerTableCards() {
		serverTableCards.clear();
	}
	public int getPoints(String slalom) {
		return Trumpf.getPoints(serverTableCards, slalom);
	}
	
	public Card getHighestUfeAbeCard(String gameType) {
		return Trumpf.highestUfeAbeCard(serverTableCards, gameType);
	}
	
	public Card getHigherSameSuitCard() {
		return Trumpf.higherSameSuitCard(serverTableCards);
	}
	
	public Trumpf evaluateTrumpf() {
		return Trumpf.evaluateTrumpf(serverTableCards);
	}
	
	public Card getHighestTrumpfCard() {
		return  Trumpf.highestTrumpf(serverTableCards);
	}
	
	public int hasLength() {
		return serverTableCards.size();
	}
	
//*********
	public String getUsername(int place) {
		return usernames.get(place);
	}
//*********

}
