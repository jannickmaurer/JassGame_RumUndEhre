package jass.server;

import jass.client.HandCards;
import jass.client.TableCards;
import jass.commons.Board;
import jass.commons.Card;

public class WinnerEvaluation {

//	public static int playersTurn = 1;
//	public int imPlayer;
	public static String trumpf;
//	public boolean wiisDone = false;
//	public Card myLastPlayedCard;
	public static String gameTyp = "Trumpf";
	private ServerTableCards serverTableCards;
	
	
	public WinnerEvaluation() {
		this.serverTableCards = new ServerTableCards();

	}
	
	public void addServerTableCard(String tableCard) {
		serverTableCards.add(new Card (tableCard));
		
	}
	
	//Sieger bekommt alle Punkte und kann Anfangen
	public void winnerIsPlayer() {
		int playerWinnerNr;
		int points;
		if (gameTyp == "Trumpf") {
			Card tempWinnerCard;
			tempWinnerCard = serverTableCards.getHighestTrumpfCard();
			for (int i = 0; i < serverTableCards.hasLength(); i++) {
				if(serverTableCards.getCard(i).toString() == tempWinnerCard.toString()) {
					playerWinnerNr = i;
				}
			}
			points = serverTableCards.getPoints();//alle vier Karten mit Punkte zusammenzählen
		}
		if (gameTyp == "ObeAbe") {}
		if (gameTyp == "UndeUfe") {}
		if (gameTyp == "Slalom") {}
		//plus 5 punkte, wenn alle 36 Karten gespielt wurden
	}
}
