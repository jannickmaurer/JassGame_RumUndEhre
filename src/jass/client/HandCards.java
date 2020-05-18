package jass.client;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Trumpf;

public class HandCards {
	ArrayList<Card> handCards = new ArrayList<Card>();
	ArrayList<Card> remainingHandCards = new ArrayList<Card>();
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
	
	public ArrayList<Card> getRemainingHandCards() {
		return remainingHandCards;
	}

	public void setHandCards(ArrayList<Card> handCards) {
		this.handCards = handCards;
	}

	public void add(Card card) {
		handCards.add(card);
		remainingHandCards.add(card);
	}
	
	public void cardPlayed(Card card) {
		int i = remainingHandCards.indexOf(card);
		remainingHandCards.remove(i);
	}

	public Trumpf evaluateTrumpf() {
		trumpf = Trumpf.evaluateTrumpf(handCards);
		return trumpf;
	}
	
	public String toString() {
		if(handCards == null) return "No Cards";
		String cards = "";
		for(Card card : handCards) {
			cards += card.toString()+"|"; 
		}
	    if (cards != null && cards.length() > 0 ) {
	    	cards = cards.substring(0, cards.length() - 1);
	    }
		return cards;
	}

}