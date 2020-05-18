package jass.commons;

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
	public static String trumpf;
	public boolean wiisDone = false;
	public Card myLastPlayedCard;
	public static String gameVariety = "Trumpf";

	HandCards handCards;
	TableCards tableCards;

	public Board(String roomName, String gameVariety) {
		// Kommt von Sämi wenn spielraum einsteigt macht er ein New Board
		this.handCards = new HandCards();
		this.tableCards = new TableCards();
		Board.gameVariety = gameVariety;
		this.enterPlayRoom(roomName);
		this.poll();

	}

	public void enterPlayRoom(String roomName) {
		// TODO server abfragen, wenn beigetretten wird
		// ev als string kette 6d;6h;9d;th split(";") trennen googlen
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

<<<<<<< HEAD
		// ask Server witch player iam mit dem roomName
		imPlayer = 1;
		
=======
		// ask Server witch player i am
		imPlayer = 2;

>>>>>>> branch 'master' of https://github.com/jannickmaurer/JassGame_RumUndEhre.git
	}

	void poll() {
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				// this.callServer(); entweder so callen oder als listerner
			}

		}, 100, 500); // Zeit für Anfrage aller erste Anfrageverzögerung, zweite wiederholzeitraum
	}
	
	public void callServer() { //statt call server als Listerner registrieeren
		this.cardListener(getTableCards());
		this.playerListener(getPlayersTurn());

	}
	
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

	
	private void play() {
		if (tableCards.hasCards() == false) {
			// evaluation Trumpf, UndeUfe, ObeAbe, Slalom
//			getGameVariety(); @Sämi bitte trumpfauswahl bei spielstart implementieren

			selectGameVariety();
			// Karte ziehen
			// select GameVariety : Auswählen was in dieser Rund gespielt wir, ob Trumpf,
			// ObeAbe, UndeUfe oder Slalom

			if (tableCards.hasCards() == true) {
				// evaluieren was ich legen darf
				// TODO evaluieren welche Karte gespielt werden darf anhand TableCards und
				// HandCards
				if (gameVariety == "Trumpf") {
					if (tableCards.evaluateTrumpf().toString() == "Trumpf") {
						// evaluieren ob ich Trumpf habe, sonst kann ich alles spielen
						if (handCards.evaluateTrumpf().toString() == "Trumpf") {
							// Play Trumpf if you have

						}
					}
					if (tableCards.evaluateTrumpf().toString() == "Stich") {
						// play all cards without lower Trumpf

					}
					if (tableCards.evaluateTrumpf().toString() == "None") {
						// play all cards
					}

					boolean test = true;
//				Player p = new Player("i weis au ned welle das i bin HAHA");
//				p.evaluateTrumpf();
					if (Trumpf.isTrumpf(null))
						test = false;
					{

					}

				}

				if (gameVariety == "ObeAbe") {
				}
				if (gameVariety == "UndeUfe") {
				}
				if (gameVariety == "Slalom") {
				}

			} else {
				// TODO KArte darf gelegt werden direkt
			}
		}
	}

	private void selectGameVariety() {
		// Methode evaluieren was der Spieler als GAmeVariante gewählt hat
		// zB Trumpf, ObeAbe, UndeUfe, Slalom
		// TODO Auto-generated method stub

	}

	public Trumpf evaluateTrumpf() {
		// TODO Auto-generated method stub
		return null;
	}

	public int winnerEval() {
		if (!wiisDone)
			wiisEval();

		// TODO Auto-generated method stub
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

		wiisDone = true;
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

}
