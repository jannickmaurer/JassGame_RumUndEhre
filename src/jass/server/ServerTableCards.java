package jass.server;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Cards;
import jass.commons.Trumpf;
import jass.commons.Card.Suit;

public class ServerTableCards extends Cards{

	ArrayList<Card> serverTableCards = new ArrayList<>();
//	private Trumpf trumpf;
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
	
	public Suit getFirstSuitOnServerTable() {
		firstSuit  = Trumpf.getFirstSuit(serverTableCards);
		return firstSuit;
	}
	
	public Card getCard(int place) {
		Card card = serverTableCards.get(place);
		return card;
	}
	
	public int getPoints() {
		return Trumpf.getPoints(serverTableCards);
	}
	
}
