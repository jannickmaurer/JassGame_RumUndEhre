package jass.client;

import java.util.ArrayList;

import jass.commons.Card;
import jass.commons.Card.Suit;
import jass.commons.Cards;
import jass.commons.Trumpf;
import jass.commons.Wiis;

public class HandCards extends Cards{
	ArrayList<Card> handCards = new ArrayList<Card>();
//	ArrayList<Card> remainingHandCards = new ArrayList<Card>();
	ArrayList<Card> startHandCards = new ArrayList<Card>();
	ArrayList<Card> playableHandCards = new ArrayList<Card>();
//	private Trumpf trumpf;
//	ArrayList<Wiis> wiis = new ArrayList<>();

	
	//!!!!!!!!ResultSendTableCArd an Board übergeben und dann handcards removen
//	Controller.getBoard.METHODE welche Karte entfernt in void Process methode

	public HandCards(ArrayList<Card> handCards) {
		super();
		this.handCards = handCards;
		this.startHandCards = handCards;
	}

	public HandCards() {
		super();
	}
	
	public void add(Card card) {
		handCards.add(card);
//		startHandCards.add(card);
	}

	public ArrayList<Card> getRemainingHandCards() {
		return handCards;  //remainingHandCards
	}
	
	public void cardPlayed(Card card) {
		int i = handCards.indexOf(card);  //remainingHandCards
		handCards.remove(i);  //remainingHandCards
	}

	public void clearPlayableHandCards() {
		playableHandCards.clear();
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
		this.handCards = handCards;
	}

	public ArrayList<Card> getPlayableHandCards() {
		return playableHandCards;
	}

	public void setPlayableHandCards(ArrayList<Card> playableHandCards) {
		this.playableHandCards = playableHandCards;
	}
	
	
	
}