package jass.client;

import java.util.ArrayList;
import java.util.logging.Logger;
import jass.commons.Card;
import jass.commons.Card.Suit;
import jass.commons.ServiceLocator;
import jass.commons.Trumpf;

/**
 * David Schürch
 * Getter und Setter Methoden für Trumpf
 */

public class HandCards {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	ArrayList<Card> handCards = new ArrayList<Card>();
	
	public void cardPlayed(Card card) {
		for (int i = 0; i < handCards.size(); i++) {
			if(handCards.get(i).toString() == card.toString()) handCards.remove(i);
		}
		logger.info("Remove card: " + card.toString());
		handCards.removeIf(i -> i.toString().equals(card.toString()));
		logger.info("Remaining HandCards in Handcards: " + handCards.toString() );
	}
	
	public ArrayList<Card> getCardHigherThanStich(Card stichCard){ 
		return Trumpf.getCardsHigherThanStich(stichCard, handCards);
	}
	
	public boolean hasSameSuitCard(Card firstTableCard) {
		return Trumpf.hasSameSuitCard(handCards, firstTableCard);
	}

	public ArrayList<Card> getHandCards() { 
		return handCards;
	}

	public void setHandCards(ArrayList<Card> handCards) {
		this.handCards = Trumpf.sortHandCards(handCards);
	}
	
	public void add(Card card) {
		logger.info("Handcard added: " + card.toString());
		this.handCards.add(card);
	}
	
	public boolean hasTrumpfCards() {
		return Trumpf.hasTrumpfCards(handCards);
	}
	
	public int hasLength() {
		return handCards.size();
	}
	
	public boolean hasCards() {
		return handCards.size() != 0;
	}
	
	public Suit getCardSuit(int i) {
		return handCards.get(i).getSuit();
	}
	
	public Card getCard(int i) {
		return handCards.get(i);
	}	
	
	public Trumpf evaluateTrumpf() {
		return Trumpf.evaluateTrumpf(handCards);
	}
	
	public String toString() {
		if (handCards == null) return "No Cards";
		String s = "";
		for (Card card : handCards) {
			s += card.toString() + "|";
		}
		if (s != null && s.length() > 0) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
}