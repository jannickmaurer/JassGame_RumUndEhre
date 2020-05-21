package jass.commons;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jass.client.TableCards;
import jass.client.HandCards;
import jass.client.Player;
import jass.commons.Card.Suit;

public class Board {
	public static int playersTurn = 1;
	public int imPlayer;
	public static String trumpf = "C";//gestzt für test
	public boolean wiisDone = false;
	public Card myLastPlayedCard;
	public static String gameTyp;
	public String playableCards;
	public HandCards playableHandCards;
	public HandCards remainingHandCards; //allefalls direkt zugriff

	HandCards handCards;
	TableCards tableCards;

	public Board(String roomName, String gameTyp) {
		this.handCards = new HandCards();
		this.tableCards = new TableCards();
		this.gameTyp = gameTyp;
//		this.enterPlayRoom(roomName);
	}

	public void enterPlayRoom(String roomName) {
		// TODO server abfragen, wenn beigetretten wird
		// identisch wie tabelCArdsFrom SErver adden für imPlayer und
		imPlayer = 1;	
		// ask Server witch player i am
		imPlayer = 2;
	}
	
	public void shuffledCardListener(String newHandCards) {
		String[] handCardList = newHandCards.split("\\|");
		for (int i = 0; i < handCardList.length; i++) {
			this.handCards.add(new Card(handCardList[i]));
		}
	}
	
	public void playerListener(int playersTurn) {
		Board.playersTurn = playersTurn;

		if (Board.playersTurn == imPlayer) { // erfragt welcher player am zug ist, falls identisch kann player
												// spielen
			play();
		}
	}

	//Methode play evaluiert nur welche Karten gespielt werden dürfen und welche nicht
	private void play() {
		playableHandCards.clearPlayableHandCards();
		if (tableCards.hasCards() == false) {
			playableHandCards = remainingHandCards;
		}
		if (tableCards.hasCards() == true) {
			if (gameTyp == "Trumpf") {
				if (tableCards.evaluateTrumpf().toString() == "Trumpf") {
					if (remainingHandCards.hasTrumpfCards()) {
						for (int i = 0; i < remainingHandCards.hasLength(); i++) {
							if (remainingHandCards.getCardSuit(i).toString() == trumpf) {
								playableHandCards.add(remainingHandCards.getCard(i));
							}
						}		
					}else playableHandCards = remainingHandCards;
				}
				if (tableCards.evaluateTrumpf().toString() == "Stich") {
					Card highestTableStichCard;
					if (remainingHandCards.hasTrumpfCards()) {
						highestTableStichCard = tableCards.getHighestTrumpfCard();
						ArrayList<Card> tempHigherThanStichCards = new ArrayList<Card> ();
						tempHigherThanStichCards = remainingHandCards.getCardHigherThanStich(highestTableStichCard);
						if (tempHigherThanStichCards != null) {
							for (Card card : tempHigherThanStichCards) {
								playableHandCards.add(card);
							}
						}
						//hier werden den möglichen überstechungskarten noch die restlichen 
						//möglichen Karten hinzugefügt der ersten tischfarbe
						for (int i = 0; i < remainingHandCards.hasLength(); i++) {
							if(remainingHandCards.getCardSuit(i).toString() == tableCards.getFirstSuit().toString()) {
								playableHandCards.add(remainingHandCards.getCard(i));
							}
						}
					}
				}
				if (remainingHandCards.evaluateTrumpf().toString() == "None") {
					for (int i = 0; i < remainingHandCards.hasLength(); i++) {
						if(remainingHandCards.getCardSuit(i).toString() == tableCards.getFirstSuit().toString()) {
							playableHandCards.add(remainingHandCards.getCard(i));
						}
					}
				}
				if (playableHandCards.hasCards() == false) playableHandCards = remainingHandCards;			
			}

			if (gameTyp == "ObeAbe" || gameTyp == "UndeUfe" || gameTyp == "Slalom") {
				for (int i = 0; i < remainingHandCards.hasLength(); i++) {
					if(remainingHandCards.getCardSuit(i).toString() == tableCards.getFirstSuit().toString()) {
						playableHandCards.add(remainingHandCards.getCard(i));
					}
				}	
				if (playableHandCards.hasCards() == false) playableHandCards = remainingHandCards;
			}
		} 
	}

	private void selectGameVariety() {
		// Methode evaluieren was der Spieler als GAmeVariante gewählt hat
		// zB Trumpf, ObeAbe, UndeUfe, Slalom
		// TODO Auto-generated method stub
	//	this.gameVariety = gameVariety;
	}

//	public Trumpf evaluateTrumpf() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public int winnerEval() {
		if (!wiisDone)
			wiisEval();

		// TODO Auto-generated method sub
		// Jeder Client prüft selbst ob er gewonnen hat
		// Danach gibt jeder Client den Punktestand zurück und SErver evaluiert welche
		// Spieler zusammen
		// gehören und passt die punktestände beider teams an

		/**
		 * Wenn ich punkte meldete muss danach der Server das Board auf null setzen
		 * clearen wenn alle vier karten liegen und server sagt welcher spieler anfängt,
		 * ist oben implementiert
		 */
		int winner = 1;
		//send winner to server;
		return winner;
	}

	private void wiisEval() {
		// TODO Auto-generated method stub
		if (!handCards.hasWiis()) wiisDone = true;
		else {
			//Server die Weisenums übergeben und den Weis an Sämi übergeben,
			//falls die Person weisen kann
		} wiisDone = true;
	}

	// Methode getTAbleCards und getPlayersTrun in Message auslagern
	private String getTableCards() {
		// TODO Auto-generated method stub
		// mit jannick absprechen worauf ich die Message und methodenaufrufe senden soll
		//
		return "6D|7H";
	}

	private int getPlayersTurn() {
		// TODO Auto-generated method stub
		// dito wie bei getTableCards
		return 1;
	}
	
	public String getPlayableCards() {//Mehoden mit playableCards werden 
		//momentan nicht verwendet, die anderen drei unten auch nicht
		return playableCards;
	}

	public void setPlayableCards(String playableCards) {
		this.playableCards = playableCards;
	}
	
	private String playableCardsAre() {
		
		return playableCards;
	}

	public int getImPlayer() {
		return imPlayer;
	}

	public void setImPlayer(int imPlayer) {
		this.imPlayer = imPlayer;
	}

	public static String getTrumpf() {
		return trumpf;
	}

	public static void setTrumpf(String trumpf) {
		Board.trumpf = trumpf;
	}

	public boolean isWiisDone() {
		return wiisDone;
	}

	public void setWiisDone(boolean wiisDone) {
		this.wiisDone = wiisDone;
	}

	public Card getMyLastPlayedCard() {
		return myLastPlayedCard;
	}

	public void setMyLastPlayedCard(Card myLastPlayedCard) {
		this.myLastPlayedCard = myLastPlayedCard;
	}

	public static String getGameTyp() {
		return gameTyp;
	}

	public static void setGameTyp(String gameTyp) {
		Board.gameTyp = gameTyp;
	}

	public HandCards getPlayableHandCards() {
		return playableHandCards;
	}

// David auskommentiert, da meiner Meinung nach nicht gebruacht!!!
	
//	public void setPlayableHandCards(HandCards playableHandCards) {
//		this.playableHandCards = playableHandCards;
//	}

	public HandCards getRemainingHandCards() {
		return remainingHandCards;
	}

	public void setRemainingHandCards(HandCards remainingHandCards) {
		this.remainingHandCards = remainingHandCards;
	}

	public HandCards getHandCards() {
		return handCards;
	}

	public void setHandCards(HandCards handCards) {
		this.handCards = handCards;
	}

	public static void setPlayersTurn(int playersTurn) {
		Board.playersTurn = playersTurn;
	}

	public void setTableCards(TableCards tableCards) {
		this.tableCards = tableCards;
	}

}
