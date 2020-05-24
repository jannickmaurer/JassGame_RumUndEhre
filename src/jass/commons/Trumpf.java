package jass.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * David Schürch
 * Die Klasse Trumpf evaluiert mit Trumpf, Stich und None ob ein Trumpf gespielt wurde, oder gestochen
 * wurde. Mit highest Trumpf wir der höchste der mitgegebenen Trümpfe der Karten zurück gegeben.
 */

public enum Trumpf { Trumpf, Stich, None;
	
	public static Trumpf evaluateTrumpf(ArrayList<Card> cards) {
			Trumpf trumpfevaluation = None;
			if (isTrumpf(cards)) trumpfevaluation = Trumpf;
			if (isStich(cards)) trumpfevaluation = Stich;			
		return trumpfevaluation;
	}
	
	public static boolean isTrumpf(ArrayList<Card> cards) {
		boolean found = false;
			if (cards.get(0).getSuit().toString().equals(Board.trumpf)) found = true;
		return found;
	}
	
	public static boolean isStich(ArrayList<Card> cards) {
		boolean found = false;
		for (int i = 1; i < cards.size() && !found; i++) {
			if (cards.get(i).getSuit().toString().equals(Board.trumpf)) found = true;}
		return found;
	}
	
	public static boolean hasTrumpfCards(ArrayList<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().toString().equals(Board.trumpf)) return true;
		}
		return false;
	}
	
	public static Card highestTrumpf(ArrayList<Card> cards) {
		String rank = "0";
		int place = -1;
		for (int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getSuit().toString().equals(Board.trumpf) && rank != "J") { 
				String cardRank = cards.get(i).getRank().toString();
				switch(cardRank) {
				case("6"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("7"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("8"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("9"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("T"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("J"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("Q"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("K"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				case("A"): if(isHigher(rank, cardRank)) rank = cardRank; place = i; break;
				}
			}	
		}
		return cards.get(place);
    }
	
	public static ArrayList<Card> getCardsHigherThanStich(Card card, ArrayList<Card> cards) {
		ArrayList<Card> returnCards = new ArrayList<>();
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().toString().equals(Board.trumpf)) {
				if (isHigher(card.getRank().toString(), cards.get(i).getRank().toString())){
					returnCards.add(cards.get(i));
				}
			}
		}
		return returnCards;
	}

	private static boolean isHigher(String cardOne, String cardTwo) {
		int cardOneRank = 0;
		int cardTwoRank = 0;
		switch(cardOne) {
			case("0"): cardOneRank = 0; break;
			case("6"): cardOneRank = 1; break;
			case("7"): cardOneRank = 2; break;
			case("8"): cardOneRank = 3; break;
			case("9"): cardOneRank = 8; break;
			case("T"): cardOneRank = 4; break;
			case("J"): cardOneRank = 9; break;
			case("Q"): cardOneRank = 5; break;
			case("K"): cardOneRank = 6; break;
			case("A"): cardOneRank = 7; break;
		}
		switch(cardTwo) {
			case("6"): cardTwoRank = 1; break;
			case("7"): cardTwoRank = 2; break;
			case("8"): cardTwoRank = 3; break;
			case("9"): cardTwoRank = 8; break;
			case("T"): cardTwoRank = 4; break;
			case("J"): cardTwoRank = 9; break;
			case("Q"): cardTwoRank = 5; break;
			case("K"): cardTwoRank = 6; break;
			case("A"): cardTwoRank = 7; break;
		}
		return cardTwoRank > cardOneRank;
	}
	
	public static boolean hasSameSuitCard(ArrayList<Card> cards, Card firstTableCard) {
		for (Card card : cards) {
			if (card.getSuit().equals(firstTableCard.getSuit())) return true;
		}
		return false;
	}
	
	public static Card higherSameSuitCard(ArrayList<Card> cards) {
		Card winnerCard = cards.get(0);
		for (int i = 0; i < cards.size()-1; i++) {
			if (cards.get(0).getSuit() == cards.get(i+1).getSuit()) {
				if (winnerCard.getRank().compareTo(cards.get(i+1).getRank()) < 0) {
					winnerCard = cards.get(i+1);
				}
			}
		}
		return winnerCard;
	}
	
	public static Card highestUfeAbeCard(ArrayList<Card> cards, String gameType) {
		Card winnerCard = cards.get(0);
		if (gameType.equals("ObeAbe")) {
			for (int i = 0; i < cards.size()-1; i++) {
				if (cards.get(0).getSuit().equals(cards.get(i+1).getSuit())) {
					if (winnerCard.getRank().compareTo(cards.get(i+1).getRank()) < 0) {
						winnerCard = cards.get(i+1);
					}
				}
			}
		}
		if (gameType.equals("UndeUfe")) {
			for (int i = 0; i < cards.size()-1; i++) {
				if (cards.get(0).getSuit().equals(cards.get(i+1).getSuit())) {
					if (winnerCard.getRank().compareTo(cards.get(i+1).getRank()) > 0) {
						winnerCard = cards.get(i+1);
					}
				}
			}
		}
		return winnerCard;
	}
	
	public static int getPoints(ArrayList<Card> cards, String gameType) {
		int points = 0;
		for (Card card : cards) {				
			switch(card.getRank().toString()) {
				case("6"): if (gameType.equals("UndeUfe")) points += 11; break;
				case("7"): break;
				case("8"): points += 8;  break;
				case("9"): break; 
				case("T"): points += 10; break; 
				case("J"): points += 2;  break;
				case("Q"): points += 3;  break; 
				case("K"): points += 4;  break; 
				case("A"): if (!gameType.equals("UndeUfe")) points += 11; break;
			}
		}
		return points;
	}
	
	public static int getTrumpfPoints(ArrayList<Card> cards, String trumpf) {
		int points = 0;
		for (Card card : cards) {				
			switch(card.getRank().toString()) {
				case("6"): break;
				case("7"): break;
				case("8"): break;
				case("9"): if (card.getSuit().toString().equals(trumpf)) points += 14; break; 
				case("T"): points += 10; break; 
				case("J"): if (card.getSuit().toString().equals(trumpf)) points += 20; 
						   else points += 2; break;
				case("Q"): points += 3; break; 
				case("K"): points += 4; break; 
				case("A"): points += 11; break;
			}
		}
		return points;
	}
	
	public static ArrayList<Card> sortHandCards(ArrayList<Card> handCards) {
		ArrayList<Card> tempHandCards = new ArrayList<>();
		ArrayList<Card> clubCards = new ArrayList<>();
		ArrayList<Card> diamondCards = new ArrayList<>();
		ArrayList<Card> heartCards = new ArrayList<>();
		ArrayList<Card> spadeCards = new ArrayList<>();
		Iterator<Card> c = handCards.iterator();
		while(c.hasNext()) {
			Card card =c.next();
			switch (card.getSuit().toString()) {
			case ("C"): clubCards.add(card); break;
			case ("D"): diamondCards.add(card); break;
			case ("H"): heartCards.add(card); break;			
			case ("S"): spadeCards.add(card); break;			
			}
		}
		Collections.sort(clubCards);
		Collections.sort(diamondCards);
		Collections.sort(heartCards);
		Collections.sort(spadeCards);
		
		for (Card card : clubCards) {
			tempHandCards.add(card);
		}
		for (Card card : diamondCards) {
			tempHandCards.add(card);
		}
		for (Card card : heartCards) {
			tempHandCards.add(card);
		}
		for (Card card : spadeCards) {
			tempHandCards.add(card);
		}
		return tempHandCards;
	}
	
	public String toString() {
		if(this == Trumpf) 	return "Trumpf";
		if(this == Stich) 	return "Stich";
		if(this == None) 	return "None";
		return null;
	}
}


	
