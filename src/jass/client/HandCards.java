package jass.client;

import java.util.ArrayList;

import jass.commons.Card;

public class HandCards {
	ArrayList<Card> handCards = new ArrayList<Card>();

	public HandCards(ArrayList<Card> handCards) {
		super();
		this.handCards = handCards;
	}

	public HandCards() {
		super();
	}

	public ArrayList<Card> getHandCards() {
		return handCards;
	}

	public void setHandCards(ArrayList<Card> handCards) {
		this.handCards = handCards;
	}

	public void add(Card card) {
		handCards.add(card);
	}

}