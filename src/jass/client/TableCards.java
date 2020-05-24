package jass.client;

import java.util.ArrayList;
import jass.commons.Card;
import jass.commons.Card.Suit;
import jass.commons.Trumpf;

public class TableCards{
	ArrayList<Card> tableCards = new ArrayList<>();

//	public TableCards() {
//		super();
//	}

	public ArrayList<Card> getTableCards() {
		return tableCards;
	}

	public boolean isComplete() { 
		return tableCards.size() == 4;
	}
	
	public Trumpf evaluateTrumpf() {
		return Trumpf.evaluateTrumpf(tableCards);
	}
	
	public boolean hasCards() {
		return tableCards.size() != 0; 
	}
	
	public Card getHighestTrumpfCard() {
		return Trumpf.highestTrumpf(tableCards);
	}
	
	public Suit getCardSuit(int i) {
		return tableCards.get(i).getSuit();
	}
	
	public void addTableCard(Card card) {
		tableCards.add(card);
	}
	
	public void clearTableCards() {
		tableCards.clear();
	}
	public Card getCard(int i) {
	return tableCards.get(i);
	}
}
