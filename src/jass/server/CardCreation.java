package jass.server;

/**
 * 
 * Karten werden auf Server erstellt
 * Danach an jeden Client gesendet
 * 
 * Die Evaluation wer wann was spielen darf und die End evalutation finden im Commons statt
 * Die Karten müssen neu gemischelt werden können nach einer Runde
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import jass.commons.*;

/**
 * This class represents a deck of playing cards. When initially created, the
 * deck is filled and shuffled. Later, the deck can be refilled and reshuffled
 * by calling the "shuffle" method.
 */

public class CardCreation {
	private final ArrayList<Card> cards = new ArrayList<>();


	/**
	 * We only ever have one deck of cards, so we do not set an ID attribute.
	 */
	public CardCreation() {
		shuffle();
//		WiisCards.setAllPlayersCards(cards);
	}

	/**
	 * Gather all 36 cards, and shuffle them
	 */
	public void shuffle() {
		// Remove all cards
		cards.clear();

		// Add all 36 cards
		String shortform = "";
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Rank rank : Card.Rank.values()) {
				shortform = suit.toString()+rank.toString();
				Card card = new Card(shortform);
//				Card card = new Card(suit, rank);
				cards.add(card);
			}
		}

		// Shuffle
		Collections.shuffle(cards);
	}

	/**
	 * Take one card from the deck and return it
	 * 
	 * This is an example of conditional assignment
	 */

	public ArrayList<Card> getShuffledCard() {
		return cards;
	}
	
	//return String mit 9 karten
	
	public String shuffledPlayerCards() {
		
		///*****
		
		
//		String shuffledPlayerCards = "";
//		if (this.cards == null) return "No Cards";
//		
//		ArrayList<Card> tempPlayerCard = new ArrayList<Card>();
//		for (int i = 0; i < 9; i++) {
//			Card card = (cards.size() > 0) ? cards.remove(cards.size() - 1) : null;
//			tempPlayerCard.add(card);
//		}
//		
//		for (int i = 0; i < 9; i++) {
//		Card card = (cards.size() > 0) ? cards.remove(cards.size() - 1) : null;
//		shuffledPlayerCards += card.toString() + "|";			
//		}
//		if (shuffledPlayerCards != null && shuffledPlayerCards.length() > 0) {
//		shuffledPlayerCards = shuffledPlayerCards.substring(0, shuffledPlayerCards.length() - 1);
//		}
//		return shuffledPlayerCards;
		
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