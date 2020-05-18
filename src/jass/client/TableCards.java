package jass.client;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Trumpf;

public class TableCards {
	ArrayList<Card> tableCards = new ArrayList<>();
	private Trumpf trumpf;

	public TableCards() {
		super();
	}

	public ArrayList<Card> getTableCards() {
		return tableCards;
	}
	
	public String getTableCardsAsString() {
		if(tableCards == null) return "No Cards";
		String cards = "";
		for(Card card : tableCards) {
			cards += card.toString()+"|"; 
		}
	    if (cards != null && cards.length() > 0 ) {
	    	cards = cards.substring(0, cards.length() - 1);
	    }
		return cards;
	}

	public void setTableCards(ArrayList<Card> tableCards) {
		this.tableCards = tableCards;
	}

	public void add(Card card) {
		tableCards.add(card);
	}
	
	public boolean hasCards() {
		return tableCards.size() != 0; //boolean evaluation ohne if direkt 0 = True
	}

	public boolean isComplete() { // brauchts die hier???
		return tableCards.size() == 4;
	}
	
	public Trumpf evaluateTrumpf() {
		trumpf = Trumpf.evaluateTrumpf(tableCards);
		return trumpf;
	}
}
