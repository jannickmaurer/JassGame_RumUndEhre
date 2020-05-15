package jass.commons;

import java.util.ArrayList;

import jass.commons.Card.Rank;
import jass.commons.Card.Suit;

public enum Trumpf { Trumpf, Stich, None;

	public static Trumpf evaluateTrumpf(ArrayList<Card> cards) {
			Trumpf trumpfevaluation = None;
			if (isTrumpf(cards)) trumpfevaluation = Trumpf;
			if (isStich(cards)) trumpfevaluation = Stich;			
		return trumpfevaluation;
	}
	
	//zum lösche erste zwei zeilen und ersetzen
	//public static String trumpf = "C";
//	public String suit = "H";
	
	
	public static boolean isTrumpf(ArrayList<Card> cards) {
		boolean found = false;
			if (cards.get(0).getSuit().toString() == Board.trumpf) found = true;
		return found;
	}
	
	public static boolean isStich(ArrayList<Card> cards) {
		boolean found = false;
		for (int i = 0; i < cards.size() && !found; i++) {
			if (cards.get(i).getSuit().toString() == Board.trumpf) found = true;}
		return found;
	}
	
	public static Suit getFirstSuit(ArrayList<Card> cards) {
		Suit searchedSuit = cards.get(0).getSuit();//Hier noch KArte mitgegeben ERT
		return searchedSuit;
	}

	
	public static Rank highestTrumpf(ArrayList<Card> cards) {
		String rank = "0";
		int place = -1;
		for (int i = 0; i<cards.size();i++) {
			if(cards.get(i).getSuit().toString() == Board.trumpf && rank != "J") {
				String value = cards.get(i).getRank().toString();
				switch(value) {
				case("6"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("7"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("8"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("9"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("T"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("J"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("Q"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("K"): if(isHigher(rank, value)) rank = value; place = i; break;
				case("A"): if(isHigher(rank, value)) rank = value; place = i; break;
				}
			}	
		}
		return cards.get(place).getRank();
    }

	private static boolean isHigher(String rank, String value) {
		int rankInt = 0;
		int valueInt = 0;
		switch(rank) {
			case("0"): rankInt = 0; break;
			case("6"): rankInt = 1; break;
			case("7"): rankInt = 2; break;
			case("8"): rankInt = 3; break;
			case("9"): rankInt = 8; break;
			case("T"): rankInt = 4; break;
			case("J"): rankInt = 9; break;
			case("Q"): rankInt = 5; break;
			case("K"): rankInt = 6; break;
			case("A"): rankInt = 7; break;
		}
		switch(value) {
			case("6"): valueInt = 1; break;
			case("7"): valueInt = 2; break;
			case("8"): valueInt = 3; break;
			case("9"): valueInt = 8; break;
			case("T"): valueInt = 4; break;
			case("J"): valueInt = 9; break;
			case("Q"): valueInt = 5; break;
			case("K"): valueInt = 6; break;
			case("A"): valueInt = 7; break;
		}
		return valueInt > rankInt;
	}
	

	//Folgend NUR Methoden zur Siegauswertung von Karten
	//Gibt höchste Karte zurück bei gleicher Farbe
	public static Rank getHigherCard(ArrayList<Card> cards) {  //, String suit
		int rank = Integer.parseInt(cards.get(0).getRank().toString());
		int place = 0;
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
		return cards.get(place).getRank();
	}
		
}
