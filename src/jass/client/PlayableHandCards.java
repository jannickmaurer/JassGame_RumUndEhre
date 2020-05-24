package jass.client;

import java.util.ArrayList;
import java.util.logging.Logger;
import jass.commons.Card;
import jass.commons.ServiceLocator;

/**
 * David Schürch
 * Getter und Setter Methoden
 */

public class PlayableHandCards {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	ArrayList<Card> playableHandCards = new ArrayList<Card>();

	public void clearPlayableHandCards() {
		playableHandCards.clear();
	}
	
	public ArrayList<Card> getPlayableHandCards() {
		return playableHandCards;
	}

	public void setPlayableHandCards(ArrayList<Card> playableHandCards) {
		for(Card card : playableHandCards)
		this.playableHandCards.add(card);
	}
	
	public void add(Card card) {
		logger.info("PlayableHandcard added: " + card.toString());
		this.playableHandCards.add(card);
	}
	
	public boolean hasPlayableCards() {
		return playableHandCards.size() != 0;
	}
	
	public boolean hasCards() {
		return playableHandCards.size() != 0;
	}

	public String toString() {
		if (playableHandCards == null) return "No Cards";
		String s = "";
		for (Card card : playableHandCards) {
			s += card.toString() + "|";
		}
		if (s != null && s.length() > 0) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
}