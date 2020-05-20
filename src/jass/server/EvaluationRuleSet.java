package jass.server;

import java.io.Serializable;
import java.util.logging.Logger;

import jass.commons.Card;
import jass.commons.ServiceLocator;

public class EvaluationRuleSet implements Serializable {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	public static String trumpf;
	public static String gameType;
	private ServerTableCards serverTableCards;;
	private static String slalom;

	public EvaluationRuleSet(String gameType) {
		this.gameType = gameType;
		serverTableCards = new ServerTableCards();
	}

	// Sieger bekommt alle Punkte und kann Anfangen
	public void winnerIsPlayer() {
		logger.info("Validate winner...");
		int playerWinnerNr;
		if (gameType == "Trumpf") {
			Card tempWinnerCard;
			tempWinnerCard = serverTableCards.getHighestTrumpfCard();
			for (int i = 0; i < serverTableCards.hasLength(); i++) {
				if (serverTableCards.getCard(i).toString() == tempWinnerCard.toString()) {
					playerWinnerNr = i;
				}
			}
		}
		if (gameType == "ObeAbe") {
			Card tempWinnerCard;

		}
		if (gameType == "UndeUfe") {
		}
		if (gameType == "Slalom") {
			if (slalom == "UndeUfe")
				slalom = "ObeAbe";
			else
				slalom = "UndeUfe";
		}
	}

	// plus 5 punkte, wenn alle 36 Karten gespielt wurden
	public int pointsForWinner() {
		if (gameType != "Slalom")
			return serverTableCards.getPoints();
		return serverTableCards.getPoints(slalom);// alle vier Karten mit Punkte zusammenzählen
	}

	public String getGameType() {
		return this.gameType;
	}

	public void addClientCard(String clientCard) {
		serverTableCards.addClientCard(clientCard);
		logger.info("Client Card added: " + clientCard) ;
	}

	public static String getTrumpf() {
		return trumpf;
	}

	public static void setTrumpf(String trumpf) {
		EvaluationRuleSet.trumpf = trumpf;
	}

	public ServerTableCards getServerTableCards() {
		return serverTableCards;
	}

	public void setServerTableCards(ServerTableCards serverTableCards) {
		this.serverTableCards = serverTableCards;
	}

	public static void setGameType(String gameType) {
		EvaluationRuleSet.gameType = gameType;
	}

}
