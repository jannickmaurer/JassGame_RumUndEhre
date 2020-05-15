package jass.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import jass.commons.Card.Suit;

public enum Wiis { 
	Stöck, Dreiblatt, Vierblatt, Fünfblatt, Sechsblatt, Siebenblatt, Achtblatt, Neunblatt,
	Viergliichi, Vierneun, Vierbuebe;
	
	//Trumpf importieren
	//Wie kann ich mehrere Blätter evaluieren und prüfen das sie nicht doppelt sind und hinzufügen zum
	//zurück geben
	public static int evaluateWiis(ArrayList<Card> cards){
		Wiis currentEval = null;
		ArrayList<Wiis> wyss = new ArrayList<>();
		int points = 0;
		if (hasStöck(cards)) points += 20;
		if (hasStöck(cards)) currentEval = Stöck; wyss.add(currentEval); //Done
		if (hasDreiblatt(cards)) currentEval = Dreiblatt; wyss.add(currentEval); //
		
		
		if (hasVierblatt(cards)) currentEval = Vierblatt; wyss.add(currentEval);
		if (hasFünfblatt(cards)) currentEval = Fünfblatt; wyss.add(currentEval);
		if (hasSechsblatt(cards)) currentEval = Sechsblatt; wyss.add(currentEval);
		if (hasSiebenblatt(cards)) currentEval = Siebenblatt; wyss.add(currentEval);//Nur ein Weiss Plus Stöcke
		if (hasAchtblatt(cards)) currentEval = Achtblatt; wyss.add(currentEval);//Nur ein Weiss Plus Stöcke evtl.
		if (hasNeunblatt(cards)) currentEval = Neunblatt; wyss.add(currentEval);//Nur ein Weiss Plus Stöcke evtl.
		if (hasViergliichi(cards)) currentEval = Viergliichi; wyss.add(currentEval);
		if (hasVierneun(cards)) currentEval = Vierneun; wyss.add(currentEval);//150
		if (hasVierbuebe(cards)) currentEval = Vierbuebe; wyss.add(currentEval);//200
		
	return points;
	}
//    public static boolean isStraight(ArrayList<Card> cards) {
//    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
//    	boolean straightFound = false;
//    	int successfulTries = 0;
//    	boolean next = true;
//    	
//    	Collections.sort(clonedCards);
//
//    		for(int i = 0; i < clonedCards.size() && next; i++) {
//    			if(clonedCards.get(i).getRank().compareTo(clonedCards.get(i+1).getRank()) == -1) {
//    				successfulTries++;
//    			} else {
//    				next = false;
//    			}
//    			if(successfulTries == 4) {
//    				next = false;
//    				straightFound = true;
//    			}	
//    		}
//    	 
//    	return straightFound;
//    }
//	

//Achtung: Evaluation mit mehreren dingen möglich zb drei Mal Dreiblatt....
//und bei Sechsblatt, wäres dann auch fünfblatt, vierblatt, dreiblatt....

	private static Suit trumpf; //static dancah probleme oder wie komme ich den Trumpf herein
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


	
	
	private static boolean hasStöck(ArrayList<Card> cards) {
		boolean found = false;
	    boolean queen = false;
	    boolean king = false;
		ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
		Iterator<Card> c = clonedCards.iterator();
		while(c.hasNext()){
			Card card = c.next();
			if(card.getSuit() != trumpf) {
				 c.remove();
			 }
		 }
	    for (int i = 0; i < clonedCards.size(); i++) {
	    	if (clonedCards.get(i).getRank().toString() == "Q") queen = true;
	    	if (clonedCards.get(i).getRank().toString() == "K") king = true;
	    }
		if (queen == true && king == true) found = true;
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
			}//wie wichtig ist default bei Fehler, sollte ja keiner passieren....
		Collections.sort(clubCards);
		Collections.sort(diamondCards);
		Collections.sort(heartCards);
		Collections.sort(spadeCards);
		}
	}
	
	private static boolean sortedCardsOnSuitQuantity(int quantity) {
		boolean found = false;
		if (clubCards.size() == quantity) found = true;
		if (diamondCards.size() == quantity) found = true;
		if (heartCards.size() == quantity) found = true;
		if (spadeCards.size() == quantity) found = true;
		return found;
	}
	
	private static void sortCardsOnRank(ArrayList<Card> cards) {
		Iterator<Card> c = cards.iterator();
		while(c.hasNext()) {
			Card card =c.next();
			switch (card.getRank().toString()) {
			case ("6"): sixCards.add(card); break;
			case ("7"): sevenCards.add(card); break;
			case ("8"): eightCards.add(card); break;
			case ("9"): nineCards.add(card); break;
			case ("T"): tenCards.add(card); break;
			case ("J"): jackCards.add(card); break;
			case ("Q"): queenCards.add(card); break;
			case ("K"): kingCards.add(card); break;
			case ("A"): aceCards.add(card); break;
			}//wie wichtig ist default bei Fehler, sollte ja keiner passieren....
		}
	}
	
	/**
	 * wie kann ich 
	 */
	private static boolean hasDreiblatt(ArrayList<Card> cards) {
		sortCardsOnSuit(cards);
		return sortedCardsOnSuitQuantity(3);
	}

	private static boolean hasVierblatt(ArrayList<Card> cards) {
		sortCardsOnSuit(cards);
		return sortedCardsOnSuitQuantity(4);
	}

	private static boolean hasFünfblatt(ArrayList<Card> cards) {
		sortCardsOnSuit(cards);
		return sortedCardsOnSuitQuantity(5);
	}

	private static boolean hasSechsblatt(ArrayList<Card> cards) {
		sortCardsOnSuit(cards);
		return sortedCardsOnSuitQuantity(6);
	}

	private static boolean hasSiebenblatt(ArrayList<Card> cards) {
		sortCardsOnSuit(cards);
		return sortedCardsOnSuitQuantity(7);
	}

	private static boolean hasAchtblatt(ArrayList<Card> cards) {
		sortCardsOnSuit(cards);
		return sortedCardsOnSuitQuantity(8);
	}

	private static boolean hasNeunblatt(ArrayList<Card> cards) {
		sortCardsOnSuit(cards);
		return sortedCardsOnSuitQuantity(9);
	}

	private static boolean hasViergliichi(ArrayList<Card> cards) {
		sortCardsOnRank(cards);
		boolean found = false;
		int count = 0; ///Eventuell Punktezahl zurück geben oder wie auch immer
		if (sixCards.size() == 4) count++; found = true;
		if (sevenCards.size() == 4) count++; found = true;
		if (eightCards.size() == 4) count++; found = true;
		if (tenCards.size() == 4) count++; found = true;
		if (queenCards.size() == 4) count++; found = true;
		if (kingCards.size() == 4) count++; found = true;
		if (aceCards.size() == 4) count++; found = true;
		//zwei mal möglich
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
	
	
	

}
