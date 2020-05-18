package jass.commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import jass.commons.Card.Suit;

public enum Wiis { 
	Stoeck, Dreiblatt, Vierblatt, Fuenfblatt, Sechsblatt, Siebenblatt, Achtblatt, Neunblatt,
	Viergliichi, Vierneun, Vierbuebe, NoWiis; //evtl NoWiis entfernen
	
	//Trumpf importieren
	//Wie kann ich mehrere Blätter evaluieren und prüfen das sie nicht doppelt sind und hinzufügen zum
	//zurück geben
	public static ArrayList<Wiis> evaluateWiis(ArrayList<Card> cards){
		Wiis currentEval; // = NoWiis;
		ArrayList<Wiis> wiis = new ArrayList<>();
		int points = 0;
		if (hasStoeck(cards)) points += 20; currentEval = Stoeck; wiis.add(currentEval); 
		if (hasDreiblatt(cards)) currentEval = Dreiblatt; wiis.add(currentEval); 
		
		
		if (hasVierblatt(cards)) currentEval = Vierblatt; wiis.add(currentEval);
		if (hasFünfblatt(cards)) points += 100; currentEval = Fuenfblatt; wiis.add(currentEval);
		if (hasSechsblatt(cards)) points += 150; currentEval = Sechsblatt; wiis.add(currentEval);
		if (hasSiebenblatt(cards)) points += 200; currentEval = Siebenblatt; wiis.add(currentEval);//Nur ein Weiss Plus Stöcke
		if (hasAchtblatt(cards)) points += 250; currentEval = Achtblatt; wiis.add(currentEval);//Nur ein Weiss Plus Stöcke evtl.
		//erledigt nach unten
		if (hasNeunblatt(cards)) points += 300;currentEval = Neunblatt; wiis.add(currentEval);//Nur ein Weiss Plus Stöcke evtl.
		if (hasViergliichi(cards)) points += countViergliichi; currentEval = Viergliichi; wiis.add(currentEval);
		if (hasVierneun(cards)) points += 150; currentEval = Vierneun; wiis.add(currentEval);//150
		if (hasVierbuebe(cards)) points += 200; currentEval = Vierbuebe; wiis.add(currentEval);//200
//	return points;
	return wiis;
	}


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
	private static int countViergliichi;

	
	
	private static boolean hasStoeck(ArrayList<Card> cards) { //ös und us entfernen
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
	
//	private static boolean sortedCardsOnSuitQuantity(int quantity) {
//		boolean found = false;
//		if (clubCards.size() == quantity) found = true;
//		if (diamondCards.size() == quantity) found = true;
//		if (heartCards.size() == quantity) found = true;
//		if (spadeCards.size() == quantity) found = true;
//		return found;
//	}
	
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
	
	

	
	private static boolean hasDreiblatt(ArrayList<Card> cards) {
//		sortCardsOnSuit(cards);
		return isCardSuitArrayListLongerThan(cards, 3);
	}
	
	private static boolean hasVierblatt(ArrayList<Card> cards) {
		return isCardSuitArrayListLongerThan(cards, 4);
	}

	private static boolean hasFünfblatt(ArrayList<Card> cards) {
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
	
	private static boolean isCardSuitArrayListLongerThan (ArrayList<Card> cards, int minLength) {
		boolean found = false;
		sortCardsOnSuit(cards);
		if(clubCards.size() >= minLength && !found) { 
			found = isBlattNum(clubCards, minLength-1);
		}
		if(diamondCards.size() >= minLength && !found) {
			found = isBlattNum(diamondCards, minLength-1);	
		}
		if(heartCards.size() >= minLength && !found) {
			found = isBlattNum(heartCards, minLength-1);	
		}
		if(spadeCards.size() >= minLength && !found) {
			found = isBlattNum(spadeCards, minLength-1);	
		}
		return found;
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
	

    
    
	private static boolean hasViergliichi(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
		sortCardsOnRank(clonedCards);
		countViergliichi = 0;
		boolean found = false;
		if (sixCards.size() == 4) countViergliichi+= 100; found = true;
		if (sevenCards.size() == 4) countViergliichi+= 100; found = true;
		if (eightCards.size() == 4) countViergliichi+= 100; found = true;
		if (tenCards.size() == 4) countViergliichi+= 100; found = true;
		if (queenCards.size() == 4) countViergliichi+= 100; found = true;
		if (kingCards.size() == 4) countViergliichi+= 100; found = true;
		if (aceCards.size() == 4) countViergliichi+= 100; found = true;
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
