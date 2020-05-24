package jass.server;

import java.util.ArrayList;
import jass.commons.Card;
import jass.commons.Trumpf;

/**
 * David Schürch
 * Getter und Setter Methoden für die Übergabe von Karten an die ArrayList.
 */

public class ServerTableCards {		

	ArrayList<Card> serverTableCards = new ArrayList<>();
	ArrayList<String> usernames = new ArrayList<>();

	public ServerTableCards() {
		super();
	}

	public ArrayList<Card> getServerTableCards() {
		return serverTableCards;
	}
	
	public boolean hasServerTableCards() {
		return serverTableCards.size() != 0; 
	}

	public int getPoints(String handType) {
		return Trumpf.getPoints(serverTableCards, handType);
	}
	
	public int getTrumpfPoints(String trumpf) {
		return Trumpf.getTrumpfPoints(serverTableCards, trumpf);
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
	
	public String getUsername(int place) {
		return usernames.get(place);
	}
}
