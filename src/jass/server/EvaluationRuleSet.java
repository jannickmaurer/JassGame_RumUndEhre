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
	private ServerTableCards serverTableCards;
	private ServerTableCards usernames;
	private static String slalom = "UndeUfe";
	private int playedRounds = 0;

	public EvaluationRuleSet(String gameType) {
		this.gameType = gameType;
		serverTableCards = new ServerTableCards();
		usernames = new ServerTableCards();
	}

	// Sieger bekommt alle Punkte und kann Anfangen
	public void winnerIsPlayer() {
		logger.info("Validate winner...");
		playedRounds++;
		int playerWinnerNr;
		if (gameType == "Trumpf") {
			if (serverTableCards.evaluateTrumpf().toString() != "None") {
				Card tempWinnerCard = serverTableCards.getHighestTrumpfCard();
				playerWinnerNr = isCardNumber(tempWinnerCard);
			}
			if (serverTableCards.evaluateTrumpf().toString() == "None") {
				Card tempWinnerCard = serverTableCards.getHigherSameSuitCard();
				playerWinnerNr = isCardNumber(tempWinnerCard);
			}
		}
		if (gameType == "ObeAbe" || gameType == "UndeUfe") {
			Card tempWinnerCard = serverTableCards.getHighestUfeAbeCard(gameType);
			playerWinnerNr = isCardNumber(tempWinnerCard);
		}
		if (gameType == "Slalom") {
			if (slalom == "UndeUfe") slalom = "ObeAbe";
			else slalom = "UndeUfe";
			Card tempWinnerCard = serverTableCards.getHighestUfeAbeCard(slalom);
			playerWinnerNr = isCardNumber(tempWinnerCard);	
		}
		//eventuell hier noch username von serverTableCards holen
	}
//***********
	public String getWinnerUsername(int winnerNr) {
		return usernames.getUsername(winnerNr);//an Stelle winnerNr winnerIsPLayer aufrufen
	}
//***********

	public int pointsForWinner() {
		if (playedRounds != 9) {//evtl modulo falls nach einer rund emit 9 karten danach weiter gezählt wird
			if (gameType != "Slalom") return serverTableCards.getPoints();
			return serverTableCards.getPoints(slalom);
			}
		else {
			if (gameType != "Slalom") return serverTableCards.getPoints()+5;
			return serverTableCards.getPoints(slalom)+5;
		}
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
	
	public int isCardNumber(Card card) {
		for (int i = 0; i < serverTableCards.hasLength(); i++) {
			if (serverTableCards.getCard(i).toString() == card.toString()) return i;
		}
		return 0;
	}
	
	public void wiisWinnerIsPlayer() {
		//spieler vergeleichen welcher den höchsten Wiis WErt hat
		//bei gleichem Wiiswert kommt der spieler dran der zuerst seine karte legte
		//Wiis wiisPlayerOne = hasTrumpf
		
		
		
	}

}
