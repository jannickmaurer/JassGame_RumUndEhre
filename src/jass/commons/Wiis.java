package jass.commons;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.sun.xml.internal.bind.v2.model.core.MaybeElement;

import jass.commons.Card.Suit;
import jass.server.EvaluationRuleSet;

public enum Wiis { 
	Dreiblatt, Vierblatt, Fuenfblatt, Sechsblatt, Siebenblatt, Achtblatt, Neunblatt, 
	FourSix, FourSeven, FourEight, FourTen, FourQueen, FourKing, FourAce, FourNaell, FourPuur;
	
// hasStoeck (ArrayL, string trumpf) 
// 
//
//
//
//	
	
	private static ArrayList<Card> clubCards = new ArrayList<Card>();
	private static ArrayList<Card> diamondCards = new ArrayList<Card>();
	private static ArrayList<Card> heartCards = new ArrayList<Card>();
	private static ArrayList<Card> spadeCards = new ArrayList<Card>();
	private static ArrayList<Card> sixCards = new ArrayList<Card>();
	private static ArrayList<Card> sevenCards = new ArrayList<Card>();
	private static ArrayList<Card> eightCards = new ArrayList<Card>();
	private static ArrayList<Card> nineCards = new ArrayList<Card>();
	private static ArrayList<Card> tenCards = new ArrayList<Card>();
	private static ArrayList<Card> jackCards = new ArrayList<Card>();
	private static ArrayList<Card> queenCards = new ArrayList<Card>();
	private static ArrayList<Card> kingCards = new ArrayList<Card>();
	private static ArrayList<Card> aceCards = new ArrayList<Card>();
	
///__________--------------_____________	
	public static int evaluateStraightWiis(ArrayList<Card> cards) {
		int straightWiisEval = 0;
		if (hasDreiblatt(cards)) straightWiisEval = 30; 
		if (hasVierblatt(cards)) straightWiisEval = 50; 
		if (hasFuenfblatt(cards)) straightWiisEval = 100; 
		if (hasSechsblatt(cards)) straightWiisEval = 150; 
		if (hasSiebenblatt(cards)) straightWiisEval = 200; 
		if (hasAchtblatt(cards)) straightWiisEval = 250; 
		if (hasNeunblatt(cards)) straightWiisEval = 300; 
		return straightWiisEval;
	}
	
	public static int evaluateFourOfAKindWiis(ArrayList<Card> cards) {
		int fourOfAKindWiisEval = 0;
		if (hasFour(cards, "6")) fourOfAKindWiisEval = 100;
		if (hasFour(cards, "7")) fourOfAKindWiisEval = 100;
		if (hasFour(cards, "8")) fourOfAKindWiisEval = 100;
		if (hasFour(cards, "T")) fourOfAKindWiisEval = 100;
		if (hasFour(cards, "Q")) fourOfAKindWiisEval = 100;
		if (hasFour(cards, "K")) fourOfAKindWiisEval = 100;
		if (hasFour(cards, "A")) fourOfAKindWiisEval = 100;
		if (hasFour(cards, "9")) fourOfAKindWiisEval = 150;
		if (hasFour(cards, "J")) fourOfAKindWiisEval = 200;
		return fourOfAKindWiisEval;
	}
	
	public static int getHighestWiis(ArrayList<Card> cards) {
		int straightPoints = evaluateStraightWiis(cards);
		int fourOfAKindPoints = evaluateFourOfAKindWiis(cards);
		if (straightPoints == fourOfAKindPoints) return straightPoints;
		else if (straightPoints > fourOfAKindPoints) return straightPoints;
		return fourOfAKindPoints;
	}
	
	public static int getWiisWinner(ArrayList<Card> cards) {
		
		return 1;
	}
	
	public static int getAllWiisesFromPlayer(ArrayList<Card> cards) {
		//evaluieren welche wiise der Player alles hat und diese zurück geben
		
		
		
		return 1;
	}
	
	public static boolean hasStoeck(ArrayList<Card> cards, String trumpf) { 
	    if (trumpf == "Trumpf") {
	    	boolean queen = false;    
	    	boolean king = false;
	    	Iterator<Card> c = cards.iterator();
	    	while(c.hasNext()){
	    		Card card = c.next();
	    		if(card.getSuit().toString() != trumpf) {
	    			c.remove();
	    		}
	    	}
	    	for (int i = 0; i < cards.size(); i++) {
	    		if (cards.get(i).getRank().toString() == "Q") queen = true;
	    		if (cards.get(i).getRank().toString() == "K") king = true;
	    	}
	    	if (queen == true && king == true) return true;
	    }
	    return false;
	}

	
	
	
	
	private static boolean hasDreiblatt(ArrayList<Card> cards) {
		return isCardSuitArrayListLongerThan(cards, 3);
	}
	private static boolean hasVierblatt(ArrayList<Card> cards) {
		return isCardSuitArrayListLongerThan(cards, 4);
	}
	private static boolean hasFuenfblatt(ArrayList<Card> cards) {
		return isCardSuitArrayListLongerThan(cards, 5);
	}
	private static boolean hasSechsblatt(ArrayList<Card> cards) { 
		return isCardSuitArrayListLongerThan(cards, 6);
	}
	private static boolean hasSiebenblatt(ArrayList<Card> cards) {
		return isCardSuitArrayListLongerThan(cards, 7);
	}
	private static boolean hasAchtblatt(ArrayList<Card> cards) {
		return isCardSuitArrayListLongerThan(cards, 8);
	}
	private static boolean hasNeunblatt(ArrayList<Card> cards) {
		return isCardSuitArrayListLongerThan(cards, 9);
	}
	
	private static boolean isCardSuitArrayListLongerThan (ArrayList<Card> cards, int wiisLength) {
		boolean found = false;
		sortCardsOnSuit(cards);
		if(clubCards.size() >= wiisLength && !found) { 
			found = isBlattNum(clubCards, wiisLength-1);
		}
		if(diamondCards.size() >= wiisLength && !found) {
			found = isBlattNum(diamondCards, wiisLength-1);	
		}
		if(heartCards.size() >= wiisLength && !found) {
			found = isBlattNum(heartCards, wiisLength-1);	
		}
		if(spadeCards.size() >= wiisLength && !found) {
			found = isBlattNum(spadeCards, wiisLength-1);	
		}
		return found;
	}
	
	private static void sortCardsOnSuit(ArrayList<Card> cards) {
		Iterator<Card> c = cards.iterator();
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
	}
	
    public static boolean isBlattNum(ArrayList<Card> cards, int blattlength) {   	
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	boolean blattFound = false;
    	int successfulTries = 0;
    	boolean next = true;
    	Collections.sort(clonedCards); //eigentlich überflüssig da die Karten bereits sortiert sind
    		for(int i = 0; i < clonedCards.size() && next; i++) {
    			if(clonedCards.get(i).getRank().compareTo(clonedCards.get(i+1).getRank()) == -1) {
    				successfulTries++;
    			} else {
    				next = false;
    			}
    			if(successfulTries == blattlength) {
    				next = false;
    				blattFound = true;
    			}	
    		}
    	return blattFound;
    }
	
    private static boolean hasFour(ArrayList<Card> cards, String rank) {
    	sortCardsOnRank(cards);
    	switch(rank) {
    	case("6"): if (sixCards.size() == 4) 	return true; break;
    	case("7"): if (sevenCards.size() == 4)	return true; break;
    	case("8"): if (eightCards.size() == 4) 	return true; break;
    	case("9"): if (nineCards.size() == 4) 	return true; break;
    	case("T"): if (tenCards.size() == 4) 	return true; break;
    	case("J"): if (jackCards.size() == 4) 	return true; break;
    	case("Q"): if (queenCards.size() == 4) 	return true; break;
    	case("K"): if (kingCards.size() == 4) 	return true; break;
    	case("A"): if (aceCards.size() == 4) 	return true; break;
    	}
    	return false; 
    }
    
	private static void sortCardsOnRank(ArrayList<Card> cards) {
		Iterator<Card> c = cards.iterator();
		while(c.hasNext()) {
			Card card = c.next();
			switch (card.getRank().toString()) {
			case ("6"): sixCards.add(card); 	break;
			case ("7"): sevenCards.add(card);	break;
			case ("8"): eightCards.add(card);	break;
			case ("9"): nineCards.add(card);	break;
			case ("T"): tenCards.add(card);		break;
			case ("J"): jackCards.add(card); 	break;
			case ("Q"): queenCards.add(card); 	break;
			case ("K"): kingCards.add(card); 	break;
			case ("A"): aceCards.add(card); 	break;
			}
		}
	}
    
    
	
//	Bis hier alle gebrauchten Methoden
//***************************************************************************

	
	private static boolean hasFourSameRank(ArrayList<Card> cards) {
		sortCardsOnRank(cards); //clonedCards
		boolean found = false;
		if (sixCards.size() == 4)  found = true; //countViergliichi+= 100;
		if (sevenCards.size() == 4)  found = true;
		if (eightCards.size() == 4)  found = true;
		if (tenCards.size() == 4)  found = true;
		if (queenCards.size() == 4)  found = true;
		if (kingCards.size() == 4)  found = true;
		if (aceCards.size() == 4)  found = true;
		return found;
	}

	private static boolean hasVierneun(ArrayList<Card> cards) {
		sortCardsOnRank(cards);
		return nineCards.size() == 4;
	}

	private static boolean hasVierbuebe(ArrayList<Card> cards) {
		sortCardsOnRank(cards);
		return jackCards.size() == 4;
	}

	
	
	private static int getPoints(Wiis wiis) {
		int points = 0;
		switch(wiis.toString()) {
		case("Dreiblatt"):	 points = 30;  break;
		case("Vierblatt"):	 points = 50;  break;
		case("Fuenfblatt"):	 points = 100; break;
		case("Sechsblatt"):  points = 150; break;
		case("Siebenblatt"): points = 200; break;
		case("Achtblatt"): 	 points = 250; break;
		case("Neunblatt"): 	 points = 300; break;
		case("FourSix"): 	 points = 100; break;
		case("FourSeven"): 	 points = 100; break;
		case("FourEight"): 	 points = 100; break;
		case("FourNaell"): 	 points = 150; break;
		case("FourTen"): 	 points = 100; break;
		case("FourPuur"):	 points = 200; break;
		case("FourQueen"):	 points = 100; break;
		case("FourKing"): 	 points = 100; break;
		case("FourAce"): 	 points = 100; break;
		}
		return points;
	}


	
	
//	public enum StraightWiis { Dreiblatt, Vierblatt, Fuenfblatt, Sechsblatt, Siebenblatt, Achtblatt, Neunblatt;
//		//gibt höchsten Wiis bei Blättern zurück
//		public static StraightWiis evaluateStraightWiis(ArrayList<Card> cards){
//			StraightWiis straightWiisEval = null;
//			if (hasDreiblatt(cards)) straightWiisEval = Dreiblatt; 
//			if (hasVierblatt(cards)) straightWiisEval = Vierblatt;
//			if (hasFünfblatt(cards)) straightWiisEval = Fuenfblatt; 
//			if (hasSechsblatt(cards)) straightWiisEval = Sechsblatt; 
//			if (hasSiebenblatt(cards)) straightWiisEval = Siebenblatt; 
//			if (hasAchtblatt(cards)) straightWiisEval = Achtblatt; 
//			if (hasNeunblatt(cards)) straightWiisEval = Neunblatt; 
//		return straightWiisEval;
//		}
//	}
//	
//	public enum FourOfAKindWiis { Four6, Four7, Four8, FourT, FourQ, FourK, FourA, Four9, FourJ;
//	//Gibt höchsten Wiis bei vier gleichen zurück
//		public static FourOfAKindWiis evaluateFourOfAKindWiis(ArrayList<Card> cards){
//			ArrayList<Wiis> fourOfAKindWiisEval = new ArrayList<>();
//		//	FourOfAKindWiis fourOfAKindWiisEval = null;
//			if (hasFour(cards, "6")) fourOfAKindWiisEval.add(Four6);
//			if (hasFour(cards, "7")) fourOfAKindWiisEval = Four7;
//			if (hasFour(cards, "8")) fourOfAKindWiisEval = Four8;
//			if (hasFour(cards, "T")) fourOfAKindWiisEval = FourT;
//			if (hasFour(cards, "Q")) fourOfAKindWiisEval = FourQ;
//			if (hasFour(cards, "K")) fourOfAKindWiisEval = FourK;
//			if (hasFour(cards, "A")) fourOfAKindWiisEval = FourA;
//			if (hasFour(cards, "9")) fourOfAKindWiisEval = Four9;
//			if (hasFour(cards, "J")) fourOfAKindWiisEval = FourJ;
//		return fourOfAKindWiisEval;
//		}
//	}
	
	public ArrayList<Wiis> getPlayerWiis(ArrayList<Card> cards){
		ArrayList<Wiis> currentPlayerWiis = new ArrayList<>();
		
		return null;
	}
	
	
//	public static int evaluateMaxWiisPoints(ArrayList<Card> cards) {
//		int maxWiisPoints = 0;
//	//	if (hasStoeck(cards))		maxWiisPoints = 20;
//		if (hasDreiblatt(cards)) 	maxWiisPoints = 30;
//		if (hasVierblatt(cards)) 	maxWiisPoints = 50;
//	//	if (hasFünfblatt(cards)) 	maxWiisPoints = 100;
//		if (hasFourSameRank(cards)) maxWiisPoints = 100;
//		if (hasSechsblatt(cards))	maxWiisPoints = 150; 
//		if (hasVierneun(cards)) 	maxWiisPoints = 150;
//		if (hasSiebenblatt(cards))	maxWiisPoints = 200; 
//		if (hasVierbuebe(cards)) 	maxWiisPoints = 200;
//		if (hasAchtblatt(cards)) 	maxWiisPoints = 250;
//		if (hasNeunblatt(cards))  	maxWiisPoints = 300;	
//		return maxWiisPoints;
//	}
	
	
	
	





	
	
//	private static boolean sortedCardsOnSuitQuantity(int quantity) {
//		boolean found = false;
//		if (clubCards.size() == quantity) found = true;
//		if (diamondCards.size() == quantity) found = true;
//		if (heartCards.size() == quantity) found = true;
//		if (spadeCards.size() == quantity) found = true;
//		return found;
//	}
	


	

	

	

	

    
    

	
	//alle mal erstellten methoden
//**************************************************************************
	
//	public static Wiis evaluateStraightWiis(ArrayList<Card> cards) {
//	Wiis straightWiisEval = null;
//	if (hasDreiblatt(cards)) straightWiisEval = Dreiblatt; 
//	if (hasVierblatt(cards)) straightWiisEval = Vierblatt; 
//	if (hasFuenfblatt(cards)) straightWiisEval = Fuenfblatt; 
//	if (hasSechsblatt(cards)) straightWiisEval = Sechsblatt; 
//	if (hasSiebenblatt(cards)) straightWiisEval = Siebenblatt; 
//	if (hasAchtblatt(cards)) straightWiisEval = Achtblatt; 
//	if (hasNeunblatt(cards)) straightWiisEval = Neunblatt; 
//	
//	return straightWiisEval;
//}

//gibt die höchste 4 gleichen Karten zurück
//public static Wiis evaluateFourOfAKindWiis(ArrayList<Card> cards) {
//	//	FourOfAKindWiis fourOfAKindWiisEval = null;
//	Wiis fourOfAKindWiisEval = null;
//	if (hasFour(cards, "6")) fourOfAKindWiisEval = FourSix;
//	if (hasFour(cards, "7")) fourOfAKindWiisEval = FourSeven;
//	if (hasFour(cards, "8")) fourOfAKindWiisEval = FourEight;
////	if (hasFour(cards, "9")) fourOfAKindWiisEval = FourNine;
//	if (hasFour(cards, "T")) fourOfAKindWiisEval = FourTen;
////	if (hasFour(cards, "J")) fourOfAKindWiisEval = FourJack;
//	if (hasFour(cards, "Q")) fourOfAKindWiisEval = FourQueen;
//	if (hasFour(cards, "K")) fourOfAKindWiisEval = FourKing;
//	if (hasFour(cards, "A")) fourOfAKindWiisEval = FourAce;
//	if (hasFour(cards, "9")) fourOfAKindWiisEval = FourNaell;
//	if (hasFour(cards, "J")) fourOfAKindWiisEval = FourPuur;
//
//	return fourOfAKindWiisEval;
//}

//public static Wiis getHighestPlayerWiis(ArrayList<Card> cards) {
//	Wiis highestPlayerWiis = null;
//	Wiis straightWiis = evaluateStraightWiis(cards);
//	Wiis fourOfAKindWiis = evaluateStraightWiis(cards);
//	if (straightWiis == null && fourOfAKindWiis == null) return null;
//	else if(straightWiis != null && fourOfAKindWiis !=null){
//		//hier vergleich, da beide etwas haben!!!!
//		if(straightWiis.getPoints() == fourOfAKindWiis.getPoints()) {
//			
//			
//			
//		}
//		
//		return null;
//		}else {
//			if (straightWiis == null) return fourOfAKindWiis;
//			return straightWiis;
//		}
//}
	
}
