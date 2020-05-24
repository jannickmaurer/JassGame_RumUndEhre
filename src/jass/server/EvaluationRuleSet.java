package jass.server;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Array;
//import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.Card;
import jass.commons.ServiceLocator;
import jass.commons.Wiis;

//result broadcast startRound message zurück an client wenn die runde startet
//andere startRound karte hier kommt gameTyp rein, welcher ich in Trumpf übergeben muss
//Allepunkte zusammenzählen für maxximale üunkte um spiel zu benden

public class EvaluationRuleSet{ //implements Serializable
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	public String trumpf = "H"; //nicht mehr statisch
	public String gameType = "Trumpf"; //nicht mehr statisch
	private ServerTableCards serverTableCards;
	private ServerTableCards usernames;
	private int playedRounds = 0;
	
	
	private ArrayList<Card> allPlayerCards;
	private int playerNumber;

	public EvaluationRuleSet() {
		serverTableCards = new ServerTableCards();
		usernames = new ServerTableCards();
	}

	// Sieger bekommt alle Punkte und kann Anfangen
	public String winnerIsPlayer() {
		logger.info("Validate winner...");
		playedRounds++;
		int playerWinnerNr = 0;
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
			if (trumpf == "UndeUfe") trumpf = "ObeAbe";
			else trumpf = "UndeUfe";
			Card tempWinnerCard = serverTableCards.getHighestUfeAbeCard(trumpf);
			playerWinnerNr = isCardNumber(tempWinnerCard);	
		}
		return serverTableCards.getUsername(playerWinnerNr);
	}

	public String getWinnerUsername(int winnerNr) {
		return usernames.getUsername(winnerNr);
	}

	public int pointsForWinner() {
		if ((playedRounds % 9) != 0) {//evtl modulo falls nach einer rund emit 9 karten danach weiter gezählt wird
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

	public String getTrumpf() { // nicht mehr static
		return trumpf;
	}

	public void setTrumpf(String trumpf) {
		this.trumpf = trumpf; //nicht mehr EvaluationRuleSet
	}

	public ServerTableCards getServerTableCards() {
		return serverTableCards;
	}

	public void setServerTableCards(ServerTableCards serverTableCards) {
		this.serverTableCards = serverTableCards;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	//	Trumpf.setGameType(gameType);
	}
	
	public int isCardNumber(Card card) {
		for (int i = 0; i < serverTableCards.hasLength(); i++) {
			if (serverTableCards.getCard(i).toString() == card.toString()) return i;
		}
		return 0;
	}
	
	public int wiisWinnerIsPlayer() {
		//spieler vergeleichen welcher den höchsten Wiis WErt hat
		//bei gleichem Wiiswert kommt der spieler dran der zuerst seine karte legte
		//Wiis wiisPlayerOne = hasTrumpf
		ArrayList<Integer> playerPoints = new ArrayList<>();
		int tempWinner = 0;
		ArrayList<Integer> tempWinnerPlace = new ArrayList<Integer>();
		for (int i = 0; i < playerNumber; i++) {
			playerPoints.add(Wiis.getHighestWiis(tempPlayerCards(i)));
		}
		for (int i = 0; i < playerPoints.size(); i++) {
			if(i > tempWinner) tempWinner = i; tempWinnerPlace.add(playerPoints.get(i));
			if(i == tempWinner) tempWinnerPlace.add(playerPoints.get(i));
		}
		if (tempWinnerPlace.isEmpty()) return -1; //achtung, aussondern
		if (tempWinnerPlace.size() == 1) return Wiis.getAllWiisesFromPlayer(tempPlayerCards(tempWinner));
//		if (tempWinnerPlace.size() > 1) Wiis.getWiisWinner(cards);
		
		return 1;
		//return + evtl has Stoeck
		
	}
	
	private ArrayList<Card> tempPlayerCards(int i){
		ArrayList<Card> temp = new ArrayList<>();
		for(int j = (i * 9); j < (9 + i * 9); j++) {
			temp.add(allPlayerCards.get(j));
		}
		return temp;
	}
	
	public void pointsForWiisWinner() {
		
		
	}

	public ArrayList<Card> getAllPlayerCards() {
		return allPlayerCards;
	}

	public void setAllPlayerCards(ArrayList<Card> allPlayerCards) {
		this.allPlayerCards = allPlayerCards;
	}
	
	//Achtung: Stoeck kann jeder immer haben, obwohl ein anderer gewiesen hat!!!!

}
