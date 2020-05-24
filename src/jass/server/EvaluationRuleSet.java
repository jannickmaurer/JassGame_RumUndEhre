package jass.server;

//<<<<<<< HEAD
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.lang.reflect.Array;
//=======
//>>>>>>> branch 'master' of https://github.com/jannickmaurer/JassGame_RumUndEhre.git
import java.util.ArrayList;
import java.util.logging.Logger;
import jass.commons.Card;
import jass.commons.ServiceLocator;

/**
 * David Schürch
 * Hier auf dem Server wird evaluiert wer der Sieger ist. Der Username des Spielers kann mit dem dem zuvor erhaltenen
 * int Wert abgefragt werden.
 */

//public class EvaluationRuleSet implements Serializable { //
public class EvaluationRuleSet{
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	public String trumpf = "H"; 
	public String gameType = "Trumpf"; 
	private ServerTableCards serverTableCards;
	private ServerTableCards usernames;
	private int playedRounds = 0;
	private ArrayList<Card> allPlayerCards;
	
	public EvaluationRuleSet() {
		serverTableCards = new ServerTableCards();
		usernames = new ServerTableCards();
	}

	public String winnerIsPlayer() {
		logger.info("Validate winner...");
		playedRounds++;
		int playerWinnerNr = 0;
		if (gameType.equals("Trumpf")) {
			if (!serverTableCards.evaluateTrumpf().toString().equals("None")) {
				Card tempWinnerCard = serverTableCards.getHighestTrumpfCard();
				playerWinnerNr = isCardNumber(tempWinnerCard);
			}
			if (serverTableCards.evaluateTrumpf().toString().equals("None")) {
				Card tempWinnerCard = serverTableCards.getHigherSameSuitCard();
				playerWinnerNr = isCardNumber(tempWinnerCard);
			}
		}
		return serverTableCards.getUsername(playerWinnerNr);
	}

	public String getWinnerUsername(int winnerNr) {
		return usernames.getUsername(winnerNr);
	}

	public int pointsForWinner() {
		logger.info("get points for: " + gameType + " " + trumpf);
		if ((playedRounds % 9) != 0) {
			switch(gameType) {
			case("Trumpf"): return serverTableCards.getTrumpfPoints(trumpf);
			case("Slalom"): return serverTableCards.getPoints(trumpf);
			default: return serverTableCards.getPoints(gameType);
			}
		}
		else {
			switch(gameType) {
			case("Trumpf"): return serverTableCards.getTrumpfPoints(trumpf) + 5;
			case("Slalom"): return serverTableCards.getPoints(trumpf) + 5;
			default: return serverTableCards.getPoints(gameType) + 5;
			}
		}
	}

	public String getGameType() {
		return this.gameType;
	}

	public void addClientCard(String clientCard) {
		serverTableCards.addClientCard(clientCard);
		logger.info("Client Card added: " + clientCard) ;
	}

	public String getTrumpf() {
		return trumpf;
	}

	public void setTrumpf(String trumpf) {
		this.trumpf = trumpf; 
	}

	public ServerTableCards getServerTableCards() {
		return serverTableCards;
	}

	public void setServerTableCards(ServerTableCards serverTableCards) {
		this.serverTableCards = serverTableCards;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	public int isCardNumber(Card card) {
		for (int i = 0; i < serverTableCards.hasLength(); i++) {
			if (serverTableCards.getCard(i).toString().equals(card.toString())) return i;
		}
		return 0;
	}

	public ArrayList<Card> getAllPlayerCards() {
		return allPlayerCards;
	}

	public void setAllPlayerCards(ArrayList<Card> allPlayerCards) {
		this.allPlayerCards = allPlayerCards;
	}
}
