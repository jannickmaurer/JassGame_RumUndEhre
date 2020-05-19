package jass.server;

import java.util.logging.Logger;

import jass.commons.Card;
import jass.commons.ServiceLocator;

public class TrumpfGame extends Playroom {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	public TrumpfGame(String name, String owner) {
		super(name, owner);
	}
	
	public static String trumpf;
	public static String gameTyp = "Trumpf";
	private ServerTableCards serverTableCards;
	
	
	public void addServerTableCard(String tableCard) {
		serverTableCards.add(new Card (tableCard));
		
	}
	
	//Sieger bekommt alle Punkte und kann Anfangen
	public void winnerIsPlayer() {
		int playerWinnerNr;
		if (gameTyp == "Trumpf") {
			Card tempWinnerCard;
			tempWinnerCard = serverTableCards.getHighestTrumpfCard();
			for (int i = 0; i < serverTableCards.hasLength(); i++) {
				if(serverTableCards.getCard(i).toString() == tempWinnerCard.toString()) {
					playerWinnerNr = i;
				}
			}
		}
		if (gameTyp == "ObeAbe") {
			Card tempWinnerCard;
			
		}
		if (gameTyp == "UndeUfe") {}
		if (gameTyp == "Slalom") {}
	}
	
	//plus 5 punkte, wenn alle 36 Karten gespielt wurden
	public int pointsForWinner() {
		return serverTableCards.getPoints();//alle vier Karten mit Punkte zusammenzählen
	}
	
	
	

}
