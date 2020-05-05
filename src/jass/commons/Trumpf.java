package jass.commons;

import java.util.ArrayList;

import jass.commons.Card.Rank;
import jass.commons.Card.Suit;

//public class Eval{

public enum Trumpf { Trumpf, Stich; //ClubTrumpf, DiamondTrumpf, HeartTrumpf, SpadeTrumpf;

	public static Trumpf evaluateTrumpf(ArrayList<Card> cards) {

			Trumpf trumpfevaluation = null;

			if (isTrumpf(cards)) trumpfevaluation = Trumpf;
	// ausserhalb programmieren siehe methode unten		if (isSuit(cards))	;
			if (isStich(cards)) trumpfevaluation = Stich;			
		return trumpfevaluation;
//		}
	}
//**************************************	
	//zum lösche erste zwei zeilen und ersetzen
	public static String trumpf = "C";
	public String suit = "H";
	
	
	public static boolean isTrumpf(ArrayList<Card> cards) {
		boolean found = false;
		if (cards.get(0).getSuit().toString() == trumpf) found = true;
		return found;
	}
	
	public static boolean isStich(ArrayList<Card> cards) {
		boolean found = false;
		for (int i = 0; i < cards.size() && !found; i++) {
					if (cards.get(i).getSuit().toString() == trumpf) found = true;}
		return found;
	}
	
	//in andere MEthode ausserhalb packen wie bei <<Player>>
	public static Suit getSuit(ArrayList<Card> cards) {
		Suit searchedSuit = cards.get(0).getSuit();
		return searchedSuit;
	}

	
	private Card highestTrumpf(ArrayList<Card> cards) {
    	//Gibt den WErt der höchsten gespielten Trumpfkarte zurück
    	//evtl boolean verwenden unten um nicht in zweite schleife zu geraten
		int rank = -1;
		int place = -1;
		for (int i = 0; i < cards.size() - 1; i++) {
			for (int j = i+1; j < cards.size(); j++) {
				if (cards.get(i).getSuit().toString() == this.trumpf && rank != 11);
					
					int value = Integer.parseInt(cards.get(i).getRank().toString());
					if(value == 6) value += 5;
					if(value == 4) value += 6;
					
					switch(value) {
						case(1): if(rank < value) rank = value; place = i; break;
						case(2): if(rank < value) rank = value; place = i; break;
						case(3): if(rank < value) rank = value; place = i; break;
						case(5): if(rank < value) rank = value; place = i; break;
						case(7): if(rank < value) rank = value; place = i; break;
						case(8): if(rank < value) rank = value; place = i; break;
						case(9): if(rank < value) rank = value; place = i; break;
						case(10): if(rank < value) rank = value; place = i; break;
						case(11): if(rank < value) rank = value; place = i; break;
					}
			}
		}
		return cards.get(place);
		
//		if(rank == 10) rank -= 6;
//		if(rank == 11) rank -= 5;
	
    		//Achtung Trumpfkarte mit Werten 
    		// 1 = 6
    		// 2 = 7
    		// 3 = 8
    		// 4 = 9 = Nell
    		// 5 = 10 
    		// 6 = Jack = Trumpf Bueb
    		// 7 = Queen 
    		// 8 = King 
    		// 9 = Ace
    }


//adaption highest rank mit Rückgabe von...
	public static Rank getHighestRankIfTrumpf(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	boolean found = false;
    	Rank searchedRank = null;
			for (int i = 0; i < cards.size() && found == true; i++) {
				switch(cards.get(i).getRank().toString()) {
				case "4": searchedRank = cards.get(i).getRank(); break;
				case "6": searchedRank = cards.get(i).getRank(); found = true; break;
				
				}
				
					
						
					
				
			}
		
		return null;
	}

	

	
	
	//Folgend NUR Methoden zur Siegauswertung von Karten
	private void isHighCard(ArrayList<Card> cards) {  //, String suit
		//Gibt höchste Karte zurück bei gleicher Farbe
		int rank = Integer.parseInt(cards.get(0).getRank().toString());
		int place = 0;
		//Farbe erste Karte auslesen
		String suit = cards.get(0).getSuit().toString();
		
//		for (int i = 0; i < cards.size(); i++) {
//			if (cards.get(i).getSuit().toString() != suit) cards.remove(i);
//		}
		for (int i = 1; i < cards.size(); i++) {
			if (cards.get(i).getSuit().toString() == suit) {
				if (Integer.parseInt(cards.get(i).getRank().toString()) > rank ) {
					rank = Integer.parseInt(cards.get(i).getRank().toString());
					place = i;
				}
			}
		}
		//return wert oder objekt zurück geben
	}

	
	
	//noch nicht gebraucht
//	@Override
//	public int compareTo(Card o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
    
//**************************************	
	
	
	
	
	
	
	
	}
	
//}
