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
		// Kommt von Sämi wenn spielraum einsteigt macht er ein New Board
		this.handCards = new HandCards();
		this.tableCards = new TableCards();
		this.gameTyp = gameTyp;
		this.enterPlayRoom(roomName);
//		this.poll();

	}

	public void enterPlayRoom(String roomName) {
		// TODO server abfragen, wenn beigetretten wird
		// identisch wie tabelCArdsFrom SErver adden für imPlayer und
		handCards.add(new Card("D6"));
		handCards.add(new Card("D7"));
		handCards.add(new Card("DA"));
		handCards.add(new Card("SQ"));
		handCards.add(new Card("CT"));

		handCards.add(new Card("S6"));
		handCards.add(new Card("HJ"));
		handCards.add(new Card("HK"));
		handCards.add(new Card("S7"));
		// ask Server witch player iam mit dem roomName
		imPlayer = 1;
		
		// ask Server witch player i am
		imPlayer = 2;

	}

//	void poll() {
//		Timer timer = new Timer();
//
//		timer.scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				// this.callServer(); entweder so callen oder als listerner
//			}
//
//		}, 100, 500); // Zeit für Anfrage aller erste Anfrageverzögerung, zweite wiederholzeitraum
//	}
	
	public void callServer() { //statt call server als Listerner registrieeren
		this.cardListener(getTableCards());
		this.playerListener(getPlayersTurn());
	}
	
	
//!!!!!!!!! Achtung hier kommen die gshuffleten Karten vonm Server am anfang.
//	!!!!!!!!!!!!!!!!!!!!!!!!! anstatt serverTableCards dann HandCards
	public void cardListener(String serverTabelCards) {
		//
		// gettabelcards und getplayersturn beim server abrfragen
		// über message server anfrage und rückmeldung der aktuellen karten
//
		String[] tableCardList = serverTabelCards.split("\\|");
		for (int i = 0; i < tableCardList.length; i++) {
			this.tableCards.add(new Card(tableCardList[i]));
		}
		if (this.tableCards.isComplete()) {
			winnerEval();
		}
	}
	
	public void playerListener(int playersTurn) {
		Board.playersTurn = playersTurn;

		if (Board.playersTurn == imPlayer) { // erfragt welcher player am zug ist, falls identisch kann player
												// spielen
			play();
		}
	}

	//handCArds gegen remaining HandCards ersetzen
	private void play() {
		playableHandCards.clearPlayableHandCards();
		//setGameVariety muss zuvor passieren und ist für alle Playspiele dieselbe (9 Runden)
		if (tableCards.hasCards() == false) {
			playableHandCards = handCards;
			// evaluation Trumpf, UndeUfe, ObeAbe, Slalom
//			getGameVariety(); @Sämi bitte trumpfauswahl bei spielstart implementieren

			selectGameVariety();
			// Karte ziehen
			// select GameVariety : Auswählen was in dieser Rund gespielt wir, ob Trumpf,
			// ObeAbe, UndeUfe oder Slalom

		if (tableCards.hasCards() == true) {
			// TODO evaluieren welche Karte gespielt werden darf anhand TableCards und
			if (gameTyp == "Trumpf") {
				if (tableCards.evaluateTrumpf().toString() == "Trumpf") {
					// evaluieren ob ich Trumpf habe, sonst kann ich alles spielen
					if (handCards.evaluateTrumpf().toString() == "Trumpf") {
						for (int i = 0; i < handCards.hasLength(); i++) {
							if (handCards.getCardSuit(i).toString() == trumpf) {
								playableHandCards.add(handCards.getCardOnPlace(i));
							};
						}		
					}else playableHandCards = handCards;
				}
				if (tableCards.evaluateTrumpf().toString() == "Stich") {
					Card highestTableStichCard;
					//Folgendes If fügt alle Trumpfkarten die gespielt werden können hinzu
					//************
					if (handCards.evaluateTrumpf().toString() == "Trumpf") {
						highestTableStichCard = tableCards.getHighestTrumpfCard();
						ArrayList<Card> tempHigherThanStichCards = new ArrayList<Card> ();
						tempHigherThanStichCards = handCards.getCardHigherThanStich(highestTableStichCard);
						for (Card card : tempHigherThanStichCards) {//was wenn tempHigherThanStichCards = null??
							playableHandCards.add(card);
						}
						//hier werden den möglichen überstechungskarten noch die restlichen 
						//möglichen Karten hinzugefügt der ersten tischfarbe
						for (int i = 0; i < handCards.hasLength(); i++) {
							if(handCards.getCardSuit(i).toString() == tableCards.getFirstSuit().toString()) {
								playableHandCards.add(handCards.getCardOnPlace(i));
							}
						}
					}
				}
				//************
				if (handCards.evaluateTrumpf().toString() == "None") {
					for (int i = 0; i < handCards.hasLength(); i++) {
						if(handCards.getCardSuit(i).toString() == tableCards.getFirstSuit().toString()) {
							playableHandCards.add(handCards.getCardOnPlace(i));
						}
					}
				}
				if (playableHandCards.hasCards() == false) playableHandCards = handCards;			
			}

			if (gameTyp == "ObeAbe" || gameTyp == "UndeUfe" || gameTyp == "Slalom") {
				for (int i = 0; i < handCards.hasLength(); i++) {
					if(handCards.getCardSuit(i).toString() == tableCards.getFirstSuit().toString()) {
						playableHandCards.add(handCards.getCardOnPlace(i));
					}
				}	
				if (playableHandCards.hasCards() == false) playableHandCards = handCards;
			}
		} 
		} //remainingCards Karte entfernen welche gespielt wurde 
		//und an Jannick übergeben. 
		//
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

	public void setPlayableHandCards(HandCards playableHandCards) {
		this.playableHandCards = playableHandCards;
	}

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

	//@Override
//	public int compareTo(Card highestStich) {
////		if (this.Card.getRank().compareTo(highestStich.getRank()) > 0) {
//			
//		}
//		return 0;
//	}
//	public int compareTo(Card o) {
//		if(this.getRank().compareTo(o.getRank()) > 0 ) {
//			return 1;
//		} else if(this.getRank().compareTo(o.getRank()) < 0) {
//				return -1;
//			} 
//		return 0;
//	}

}
