package jass.client;

import java.util.ArrayList;

import jass.commons.Card;

public class TableCards {
	ArrayList<Card> tableCards = new ArrayList<Card>();

	public TableCards(ArrayList<Card> tableCards) {
		super();
		this.tableCards = tableCards;
	}

	public TableCards() {
		super();
	}

	public ArrayList<Card> getTableCards() {
		return tableCards;
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

	public boolean isComplete() {
		return tableCards.size() == 4;
	}
}
