package jass.server;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Cards;
import jass.commons.Trumpf;

public class ServerTableCards extends Cards{

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
		return Trumpf.getPoints(serverTableCards);
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
	
//*********
	public String getUsername(int ordinal) {
		return usernames.get(ordinal);
	}
//*********

}
