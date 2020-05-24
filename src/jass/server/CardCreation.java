package jass.server;


import java.util.ArrayList;
import java.util.Collections;
import jass.commons.*;

/**
 * David Schürch 
 * Klasse von PokerGame übernommen mit kleinen Anpassungen
 */

public class CardCreation {
	private final ArrayList<Card> cards = new ArrayList<>();

	public CardCreation() {
		shuffle();
	}

	public void shuffle() {
		cards.clear();
		String shortform = "";
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Rank rank : Card.Rank.values()) {
				shortform = suit.toString()+rank.toString();
				Card card = new Card(shortform);
				cards.add(card);
			}
		}
		Collections.shuffle(cards);
	}

	public ArrayList<Card> getShuffledCard() {
		return cards;
	}
	
	public String shuffledPlayerCards() {
		
		String shuffledPlayerCards = "";
		if (this.cards == null) return "No Cards";
		for (int i = 0; i < 9; i++) {
			Card card = (cards.size() > 0) ? cards.remove(cards.size() - 1) : null;
			shuffledPlayerCards += card.toString() + "|";			
		}
		if (shuffledPlayerCards != null && shuffledPlayerCards.length() > 0) {
			shuffledPlayerCards = shuffledPlayerCards.substring(0, shuffledPlayerCards.length() - 1);
		}
		return shuffledPlayerCards;
	}
}