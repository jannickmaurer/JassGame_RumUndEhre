package jass.commons;

import java.util.ArrayList;

import jass.commons.Card.Rank;
import jass.commons.Card.Suit;
import jass.server.EvaluationRuleSet;

public enum Trumpf { Trumpf, Stich, None;

	public static Trumpf evaluateTrumpf(ArrayList<Card> cards) {
			Trumpf trumpfevaluation = None;
			if (isTrumpf(cards)) trumpfevaluation = Trumpf;
			if (isStich(cards)) trumpfevaluation = Stich;			
		return trumpfevaluation;
	}

	
	public static boolean isTrumpf(ArrayList<Card> cards) {
		boolean found = false;
			if (cards.get(0).getSuit().toString() == Board.trumpf) found = true;
		return found;
	}
	
	//anpassung for schleife startet bei 1 , da vorher kein Stich möglich sondern trumpf
	public static boolean isStich(ArrayList<Card> cards) {
		boolean found = false;
		for (int i = 1; i < cards.size() && !found; i++) {
			if (cards.get(i).getSuit().toString() == Board.trumpf) found = true;}
		return found;
	}
	
	public static boolean hasTrumpfCards(ArrayList<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().toString() == Board.trumpf) return true;
		}
		return false;
	}
	
	public static Card highestTrumpf(ArrayList<Card> cards) {
		String rank = "0";
		int place = -1;
		for (int i = 0; i < cards.size(); i++) {
			//wenn von Server zugriff erfolgt geht evaluation Board.trumpf ?? besser mit Variablen??
			if(cards.get(i).getSuit().toString() == Board.trumpf && rank != "J") { 
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
	
	//entnimmt erste Karte, welche höchster Stich ist und 
	public static ArrayList<Card> getCardsHigherThanStich(Card card, ArrayList<Card> cards) {
		ArrayList<Card> returnCards = new ArrayList<>();
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getSuit().toString() == Board.trumpf) {
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
	
//	public static Card getHigherCard(ArrayList<Card> cards) {
//		Card card;
//		for (int i = 1; i < cards.size()-1; i++) {
//			if (cards.get(i).getSuit() == cards.get(0).getSuit()) {
//				if (isHigher(card.getRank().toString(), cards.get(i).getRank().toString())){
//	//				returnCards.add(cards.get(i));
//				}
//			}
//		}
//		
//		return null;
//	}
	
//	public static Suit getFirstSuit(ArrayList<Card> cards) { //evtl löschen, wird nur 2mal verwendet aktuell
//		return cards.get(0).getSuit();
//	}
	
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
	
	public static Card highestUfeAbeSlalomCard(ArrayList<Card> cards, String gameTyp) {
		Card winnerCard = cards.get(0);
		if (gameTyp == "ObeAbe") {
			for (int i = 0; i < cards.size()-1; i++) {
				if (cards.get(0).getSuit() == cards.get(i+1).getSuit()) {
					if (winnerCard.getRank().compareTo(cards.get(i+1).getRank()) < 0) {
						winnerCard = cards.get(i+1);
					}
				}
			}
		}
		if (gameTyp == "UndeUfe") {
			for (int i = 0; i < cards.size()-1; i++) {
				if (cards.get(0).getSuit() == cards.get(i+1).getSuit()) {
					if (winnerCard.getRank().compareTo(cards.get(i+1).getRank()) > 0) {
						winnerCard = cards.get(i+1);
					}
				}
			}
		}
		if (gameTyp == "Slalom") {
			//muss wissen was erste Karte war, obeAbe oder undeUfe
			//evtl. Boolean zum evaluieren ob obeabe oder unde ufe

		}
		return winnerCard;
	}
	
	
	
	public static int getPoints(ArrayList<Card> cards) {
		int points = 0;
		for (Card card : cards) {				
			switch(card.getRank().toString()) {
				case("6"): if (EvaluationRuleSet.gameType == "UndeUfe") points += 11; break;
				case("7"): points += 0; break;
				case("8"): if (EvaluationRuleSet.gameType == "ObeAbe") points += 8; 
						   if (EvaluationRuleSet.gameType == "UndeUfe") points += 8; break;
				case("9"): if (card.getSuit().toString() == Board.trumpf) points += 14; break; 
				case("T"): points += 10; break; 
				case("J"): if (card.getSuit().toString() == Board.trumpf) points += 20; 
						   else points += 2; break;
				case("Q"): points += 3; break; 
				case("K"): points += 4; break; 
				case("A"): if (EvaluationRuleSet.gameType != "UndeUfe") points += 11; break;
			}
		}
		return points;
	}
	
	//Methode überladen, hier nur für Slalom, welcher zusatzparameter braucht
	public static int getPoints(ArrayList<Card> cards, String slalom) {
		int points = 0;
		for (Card card : cards) {				
			switch(card.getRank().toString()) {
				case("6"): if (slalom == "UndeUfe") points += 11; break;
				case("7"): break;
				case("8"): points += 8; break;
				case("9"): break; 
				case("T"): points += 10; break; 
				case("J"): points += 2; break;
				case("Q"): points += 3; break; 
				case("K"): points += 4; break; 
				case("A"): if (slalom != "UndeUfe") points += 11; break;
			}
		}
		return points;
	}
}


	
