package jass.client;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Trumpf;

public class HandCards {
	ArrayList<Card> handCards = new ArrayList<Card>();
	private Trumpf trumpf;

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

	public Trumpf evaluateTrumpf() {
		trumpf = Trumpf.evaluateTrumpf(handCards);
		return trumpf;
	}

}