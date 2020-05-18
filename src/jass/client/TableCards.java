package jass.client;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Card.Suit;
import jass.commons.Cards;
import jass.commons.Trumpf;

public class TableCards extends Cards{
	ArrayList<Card> tableCards = new ArrayList<>();
//	private Trumpf trumpf;
	private Suit firstSuit;

	public TableCards() {
		super();
	}

	public ArrayList<Card> getTableCards() {
		return tableCards;
	}
	
	public boolean hasCards() {
		return tableCards.size() != 0; //boolean evaluation ohne if direkt 0 = True
	}

	public boolean isComplete() { // brauchts die hier???
		return tableCards.size() == 4;
	}
	
//	public Trumpf evaluateTrumpf() {
//		trumpf = Trumpf.evaluateTrumpf(tableCards);
//		return trumpf;
//	}
	
	public Suit getFirstSuit() {
		firstSuit  = Trumpf.getFirstSuit(tableCards);
		return firstSuit;
	}
}
