package jass.commons;

import java.util.ArrayList;
import java.util.Collections;

public class WiisCards {

	ArrayList<Wiis> wiis = new ArrayList<>();
	ArrayList<Card> allPlayersCards = new ArrayList<>();
	
	
	public WiisCards() {
		super();
	}
	
	public void setAllPlayersCards(ArrayList<Card> allPlayersCards) {
		allPlayersCards.clear();

//TEST Alle Karten anlegen
		String testCard = "";
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Rank rank : Card.Rank.values()) {
				testCard = suit.toString()+rank.toString();
				Card card = new Card(testCard);
				allPlayersCards.add(card);
			}
		}
		Collections.shuffle(allPlayersCards);
//ENDETEST
	}

	public ArrayList<Wiis> getCards() {
		return wiis;
	}

	public void setCards(ArrayList<Wiis> wiis) {
		this.wiis = wiis;
	}
	
	public boolean hasWiis () {
		return wiis.size() != 0;
	}
	
	//Klasse Account getCurrentPlayerCards respektive set für Player Cards für wiis evaluation
	
//***************************Ab hier neue befehle programmiert, obere noch checken ob ich sie brauche
//	public Wiis getStraightWiis() {
//		return evaluateStraightWiis(cards);
//	}
	
	public boolean hasStoeck() {
	return Wiis.hasStoeck(allPlayersCards);
	}
	
	//gibt punktewert des maximalen weises des spielers zurück
	public int playerWiisPoints(int playerNr) {
		ArrayList<Card> tempCards = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			tempCards.add(allPlayersCards.get(i+(playerNr-1)*9));
		}
		return Wiis.evaluateMaxWiisPoints(tempCards);
	}
	//TODO evaluieren welcher spieler bei mehreren weisen den höchsten hat
	//Wichtig: jetzt wird nur der höchste weis geprüft, danach müssten noch mögliche restliche weise zusammen gezählt werden vom gleichen spieler
	
	
}
