package jass.commons;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import jass.client.TableCards;
import jass.client.HandCards;
import jass.client.Player;
import jass.commons.Card.Suit;

public class Board {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	public static int playersTurn;
	public int imPlayer;
	public static String trumpf = "H";
	public boolean wiisDone = false;
	public Card myLastPlayedCard;
	public static String gameTyp = "Trumpf";
	public String playableCards;
	public HandCards playableHandCards;
	public HandCards handCards; //allefalls direkt zugriff
//	public HandCards startHandCards;
	private String playerOnTurn;
	private int playedCards;
	private ArrayList<String> members = new ArrayList<>();
	
//	HandCards handCards;
	private TableCards tableCards;

	public Board(String roomName, String gameTyp) {
		this.handCards = new HandCards();
		this.tableCards = new TableCards();
		this.gameTyp = gameTyp;
		this.playedCards = 0;
//		this.enterPlayRoom(roomName);
	}

//	public void enterPlayRoom(String roomName) {
//		// TODO server abfragen, wenn beigetretten wird
//		// identisch wie tabelCArdsFrom SErver adden für imPlayer und
//		imPlayer = 1;	
//		// ask Server witch player i am
//		imPlayer = 2;
//	}
//	
	public void shuffledCardListener(String newHandCards) {
		ArrayList<Card> tempHandCards = new ArrayList<>();
		logger.info(newHandCards);
		String[] handCardList = newHandCards.split("\\|");
		for (int i = 0; i < handCardList.length; i++) {
			tempHandCards.add(new Card(handCardList[i]));

	//		this.handCards.add(new Card(handCardList[i]));
//			this.startHandCards.add(new Card (handCardList[i]));
			//this.startHandCards.add(new Card (handCardList[i]));
		}
		handCards.setHandCards(tempHandCards);
		logger.info("HandCards added: " + handCards.toString());
		
	}
	
	public void playerListener(int playersTurn) {
		Board.playersTurn = playersTurn;

		if (Board.playersTurn == imPlayer) { // erfragt welcher player am zug ist, falls identisch kann player
												// spielen
			play();
		}
	}

	//Methode play evaluiert nur welche Karten gespielt werden dürfen und welche nicht
	public void play() {
		if(playableHandCards != null) playableHandCards.clearPlayableHandCards();
		if (tableCards.hasCards() == false) {
		//	playableHandCards = handCards;
			playableHandCards.setPlayableHandCards(handCards.getHandCards());
		}
		if (tableCards.hasCards() == true) {
			if (gameTyp == "Trumpf") {
				if (tableCards.evaluateTrumpf().toString() == "Trumpf") {
					if (handCards.hasTrumpfCards()) {
						for (int i = 0; i < handCards.hasLength(); i++) {
							if (handCards.getCardSuit(i).toString() == trumpf) {
								playableHandCards.add(handCards.getCard(i));
							}
						}		
					}else playableHandCards.setPlayableHandCards(handCards.getHandCards());
				}
				if (tableCards.evaluateTrumpf().toString() == "Stich") {
					Card highestTableStichCard;
					if (handCards.hasTrumpfCards()) {
						highestTableStichCard = tableCards.getHighestTrumpfCard();
						ArrayList<Card> tempHigherThanStichCards = new ArrayList<Card> ();
						tempHigherThanStichCards = handCards.getCardHigherThanStich(highestTableStichCard);
						if (tempHigherThanStichCards != null) {
							for (Card card : tempHigherThanStichCards) {
								playableHandCards.add(card);
							}
						}
						if (handCards.hasSameSuitCard(tableCards.getCard(0))) {
							for (int i = 0; i < handCards.hasLength(); i++) {
								if(handCards.getCardSuit(i) == tableCards.getCardSuit(0)) {
									playableHandCards.add(handCards.getCard(i));
								}
							}
						} else {
							for (int i = 0; i < handCards.hasLength(); i++) {
								if(handCards.getCardSuit(i) != tableCards.getCardSuit(0) &&
							 	   handCards.getCardSuit(i).toString() != trumpf) {
									playableHandCards.add(handCards.getCard(i));
								}
							}
						}
					}							
				}
				if (handCards.evaluateTrumpf().toString() == "None") {
					for (int i = 0; i < handCards.hasLength(); i++) {
						if(handCards.getCardSuit(i) == tableCards.getCardSuit(0)) {
							playableHandCards.add(handCards.getCard(i));
						}
					}
				}
				if (playableHandCards.hasPlayableCards() == false) {
					playableHandCards.setPlayableHandCards(handCards.getHandCards()); 		
				}
			}

			if (gameTyp == "ObeAbe" || gameTyp == "UndeUfe" || gameTyp == "Slalom") {
				for (int i = 0; i < handCards.hasLength(); i++) {
					if(handCards.getCardSuit(i) == tableCards.getCardSuit(0)) {
						playableHandCards.add(handCards.getCard(i));
					}
				}	
				if (playableHandCards.hasPlayableCards() == false) {
					playableHandCards.setPlayableHandCards(handCards.getHandCards());
				}
			}
		} 
	}

//	private void selectGameVariety() {
		// Methode evaluieren was der Spieler als GAmeVariante gewählt hat
		// zB Trumpf, ObeAbe, UndeUfe, Slalom
		// TODO Auto-generated method stub
	//	this.gameVariety = gameVariety;
//	}

//	public Trumpf evaluateTrumpf() {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public int winnerEval() {
////		if (!wiisDone)
////			wiisEval();
//
//		// TODO Auto-generated method sub
//		// Jeder Client prüft selbst ob er gewonnen hat
//		// Danach gibt jeder Client den Punktestand zurück und SErver evaluiert welche
//		// Spieler zusammen
//		// gehören und passt die punktestände beider teams an
//
//		/**
//		 * Wenn ich punkte meldete muss danach der Server das Board auf null setzen
//		 * clearen wenn alle vier karten liegen und server sagt welcher spieler anfängt,
//		 * ist oben implementiert
//		 */
//		int winner = 1;
//		//send winner to server;
//		return winner;
//	}

//	private void wiisEval() {
//		// TODO Auto-generated method stub
//		if (!handCards.hasWiis()) wiisDone = true;
//		else {
//			//Server die Weisenums übergeben und den Weis an Sämi übergeben,
//			//falls die Person weisen kann
//		} wiisDone = true;
//	}

	public void addTableCard(Card card) {
		logger.info("Add Card to TableCards: " + card.toString());
		this.tableCards.add(card);
		this.playedCards++;
		if(playedCards == members.size()) {
			playedCards = 0;
			tableCards.clearTableCards();
		}
	}
	
	private TableCards getTableCards() {
		return this.tableCards;
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

//	public Card getMyLastPlayedCard() {
//		return myLastPlayedCard;
//	}
//
//	public void setMyLastPlayedCard(Card myLastPlayedCard) {
//		this.myLastPlayedCard = myLastPlayedCard;
//	}

	public static String getGameTyp() {
		return gameTyp;
	}

	public static void setGameTyp(String gameTyp) {
		Board.gameTyp = gameTyp;
	}

	public HandCards getPlayableHandCards() {
		return playableHandCards;
	}

	
//	public void setPlayableHandCards(HandCards playableHandCards) {
//		this.playableHandCards = playableHandCards;
//	}

	public HandCards getRemainingHandCards() {
		return handCards;
	}

//	public void setRemainingHandCards(HandCards remainingHandCards) {
//		this.remainingHandCards = remainingHandCards;
//	}

	
//Getter und Setter
	public HandCards getHandCards() {
		return handCards;
	}
	
	public ArrayList<Card> getHandCardsList() {
		return handCards.getHandCards();
	}

//	public void setHandCards(HandCards handCards) {
//		this.handCards = handCards;
//	}

	public static void setPlayersTurn(int playersTurn) {
		Board.playersTurn = playersTurn;
	}

//	public void setTableCards(TableCards tableCards) {
//		this.tableCards = tableCards;
//	}
	
	public void removeHandCard(Card card) {
		logger.info("Board removes handcard: " + card.toString() );
			this.handCards.cardPlayed(card);
	}
	
	public void setMembers(ArrayList<String> members) {
		for(String m : members) {
			this.members.add(m);
		}
	}

}
