package jass.client;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Card.Suit;
import jass.commons.Cards;
import jass.commons.Trumpf;

public class HandCards extends Cards{
	ArrayList<Card> handCards = new ArrayList<Card>();
	ArrayList<Card> remainingHandCards = new ArrayList<Card>();
	ArrayList<Card> playableHandCards = new ArrayList<Card>();
//	private Trumpf trumpf;

	public HandCards(ArrayList<Card> handCards) {
		super();
		this.handCards = handCards;
	}

	public HandCards() {
		super();
	}

	public ArrayList<Card> getRemainingHandCards() {
		return remainingHandCards;
	}
	
	public void cardPlayed(Card card) {
		int i = remainingHandCards.indexOf(card);
		remainingHandCards.remove(i);
	}

//	public Trumpf evaluateTrumpf() {
//		trumpf = Trumpf.evaluateTrumpf(handCards);
//		return trumpf;
//	}

	public void clearPlayableHandCards() {
		playableHandCards.clear();
	}
	
	//methode um höhere Karten als Stich zurück zu geben
	public ArrayList<Card> getCardHigherThanStich(Card stichCard){ //ArrayList<Card> cards
		ArrayList<Card> returnCards = new ArrayList<>();
		returnCards = Trumpf.getCardsHigherThanStich(stichCard, handCards);
		return returnCards;
	}
	
	
}