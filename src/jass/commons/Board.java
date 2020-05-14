package jass.commons;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jass.client.TableCards;
import jass.client.HandCards;
import jass.commons.Card.Suit;

public class Board {
	public static int playersTurn = 1;
	public int imPlayer;
	public static String trumpf;
	public static int playedTableCards = 0;
	
	public String gameVariety = "Trumpf";  

	HandCards handCards = new HandCards();
	TableCards tableCards = new TableCards();

	public void enterPlayRoom(String roomName) { // Kommt von Sämi wenn spielraum einsteigt
		// TODO server abfragen, wenn beigetretten wird
		// ev als string kette 6d;6h;9d;th split(";") trennen googlen
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
		
		
		

	}

	void poll() {
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				// gettabelcards und getplayersturn beim server abrfragen
				// über message server anfrage und rückmeldung der aktuellen karten
				getTableCards();
				getPlayersTurn();
				
				
				if (playersTurn == imPlayer) { //erfragt welcher player am zug ist, falls identisch kann player spielen
					play();
				}
				if (tableCards.isComplete()) {
					winnerEval();
				}
				//ABfrage ob Tablecards nach erster Runde um Weisen zu können
				if (playedTableCards == 4 ) {
					wiisEval();
				}
			}
		}, 100, 500); //Zeit für Anfrage aller erste Anfrageverzögerung, zweite wiederholzeitraum

	}

	private void play() {
		if (tableCards.hasCards() == false ) {
			//evaluation Trumpf, UndeUfe, ObeAbe, Slalom
			
//			getGameVariety(); @Sämi bitte trumpfauswahl bei spielstart implementieren
			
			if (gameVariety == "Trumpf") {
				
				
			}
				// logik um Trumpf zu bestimmen Rückmeldung view
				// view box triggern welcher Spielmethode ob Trumpf obeAbe undeUfe, etc
	
				trumpf = "H";
				playedTableCards++;
			}
			
			if (gameVariety == "ObeAbe") {}
			if (gameVariety == "UndeUfe") {}
			if (gameVariety == "Slalom") {}
//evtl hier else
			
		if (tableCards.hasCards()) {
			// evaluieren was ich legen darf
			// TODO evaluieren welche Karte gespielt werden darf anhand TableCards und
			// HandCards
//			String s;
//			s = Trumpf.getSuit(null).toString();
			if (gameVariety == "Trumpf") {
				//
				boolean test = true;
				evaluateTrumpf();
	//			Trumpf.evaluateTrumpf();
				if(Trumpf.isTrumpf(null)) test = false;{
					
				}
				
			}
			
			if (gameVariety == "ObeAbe") {}
			if (gameVariety == "UndeUfe") {}
			if (gameVariety == "Slalom") {}
			
			
			playedTableCards++;
		} else {
			// TODO KArte darf gelegt werden direkt
			playedTableCards++;
		}

	}

	public Trumpf evaluateTrumpf() {
		// TODO Auto-generated method stub
		return null;
	}

	private void winnerEval() {
		// TODO Auto-generated method stub
		//Jeder Client prüft selbst ob er gewonnen hat
		// Danach gibt jeder Client den Punktestand zurück und SErver evaluiert welche Spieler zusammen
		//gehören und passt die punktestände beider teams an
		
		/**
		 * Wenn ich punkte meldete muss danach der Server das Board auf null setzen
		 * clearen wenn alle vier karten liegen und server sagt welcher spieler anfängt, ist oben
		 * implementiert
		 */

	}
	
	private void wiisEval() {
		// TODO Auto-generated method stub
		
	}
	private void getTableCards() {
		// TODO Auto-generated method stub
		//mit jannick absprechen worauf ich die Message und methodenaufrufe senden soll
	}

	private void getPlayersTurn() {
		// TODO Auto-generated method stub
		//dito wie bei getTableCards
	}
	
}
