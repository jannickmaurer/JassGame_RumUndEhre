package jass.server;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Cards;
import jass.commons.Trumpf;
import jass.commons.Card.Suit;

public class ServerTableCards extends Cards{

	ArrayList<Card> serverTableCards = new ArrayList<>();
	private Trumpf trumpf;
	private Suit firstSuit;

	public ServerTableCards() {
		super();
	}

	public ArrayList<Card> getSercerTableCards() {
		return serverTableCards;
	}
	
	public boolean hasServerTableCards() {
		return serverTableCards.size() != 0; //boolean evaluation ohne if direkt 0 = True
	}

	public boolean isComplete() { // brauchts die hier???
		return serverTableCards.size() == 4;
	}
	

	
//	public Suit getFirstSuitOnServerTable() {
//		firstSuit  = Trumpf.getFirstSuit(serverTableCards);
//		return firstSuit;
//	}
	
	public int getPoints() {
		return Trumpf.getPoints(serverTableCards);
	}
	
	
	//Add a card to severTable in format: Jannick:D9
	public void addClientCard(String clientCard) {
		String[] temp = clientCard.split("\\:");
		
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
	
}
