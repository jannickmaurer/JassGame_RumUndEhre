package jass.commons;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Card.Suit;

public class Cards {

	ArrayList<Card> cards = new ArrayList<>();
	ArrayList<Wiis> wiis = new ArrayList<>();

	private Trumpf trumpf;
	private Card highestTrumpfCard;


	public Cards() {
		super();
	}

	@Override
	public String toString() {
		if (cards == null) return "No Cards";
		String s = "";
		for (Card card : cards) {
			s += card.toString() + "|";
		}
		if (s != null && s.length() > 0) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setWiis(ArrayList<Wiis> wiis) {
		this.wiis = wiis;
	}
	
	public ArrayList<Wiis> getWiis() {
		return wiis;
	}

	public void add(Card card) {
		cards.add(card);
	}

	public boolean hasCards() {
		return cards.size() != 0; // boolean evaluation ohne if
	}
	
	public boolean hasWiis () {
		return wiis.size() != 0;
	}
	
	public int hasLength() {
		return cards.size();
	}
	
	public Suit getCardSuit(int i) {
		Suit suit = cards.get(i).getSuit();
		return suit;
	}
	
	public Card getCardOnPlace(int i) {
		return cards.get(i);
	}
	
	public Trumpf evaluateTrumpf() {
		trumpf = Trumpf.evaluateTrumpf(cards);
		return trumpf;
	}
	
	public Card getHighestTrumpfCard() {
		highestTrumpfCard = Trumpf.highestTrumpf(cards);
		return highestTrumpfCard;
	}
	
}
