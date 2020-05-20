package jass.server;

import java.util.logging.Logger;

import jass.commons.Card;
import jass.commons.ServiceLocator;

public class TrumpfGame extends Playroom {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	protected final String gameType = "Trumpf";
	
	public static String trumpf;
	
	public static String gameTyp = "Trumpf";
	private ServerTableCards serverTableCards;
;
	private static String slalom;
	private int playedCards;
	
	public TrumpfGame(String name, String owner) {
		super(name, owner);
		playedCards = 0;
		serverTableCards = new ServerTableCards();
	}

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
		if (gameTyp == "Slalom") {
			if(slalom == "UndeUfe") slalom = "ObeAbe";
			else slalom = "UndeUfe";
		}
	}
	
	//plus 5 punkte, wenn alle 36 Karten gespielt wurden
	public int pointsForWinner() {
		if (gameTyp != "Slalom") return serverTableCards.getPoints();
		return serverTableCards.getPoints(slalom);//alle vier Karten mit Punkte zusammenzählen
	}
	
	public String getGameType() {
		return this.gameType;
	}
	
	@Override
	public void addClientCard(String clientCard) {
		if(playedCards < this.getMembers().size()) {
			logger.info("ClientCard added to ServerTableCards: " + clientCard) ;
			serverTableCards.addClientCard(clientCard);
			playedCards++;
		}
		if(playedCards == this.getMembers().size()) {
			serverTableCards.addClientCard(clientCard);
			winnerIsPlayer();
			playedCards = 0;
			serverTableCards.clearServerTableCards();
		}
		
	}
	



}
