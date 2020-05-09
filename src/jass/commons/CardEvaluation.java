package jass.commons;

import java.util.ArrayList;

import jass.commons.Card;

/**
 * 
 * @author Skar
 *
 */
public class CardEvaluation implements Comparable<Card>{ // brauchts das comparable, bis jetzt nicht??

    //Ausgang Eingabe von vier Karten mit Zahl von 1-9 und Buchstabe C,D,H oder S
	//Trumpf Suit
	
	public String trumpf = "C";
	public String suit = "H";
	private final ArrayList<Card> currentEval = new ArrayList<>();

    
	
	
	public boolean getFirstCardTrumpf(ArrayList<Card> cards) {
		boolean card = isFirstCardTrumpf(cards);
		return card;
	}
	
	
//    public Card CardEvalutaion(ArrayList<Card> cards){   
////    	this.currentEval = cards;
//    	
//    	//Keine Trumpfkarte gespielt
//    	if (isTrumpf(cards) == false) isSuit();
//    	
//    	
//    	if (isTrumpf(cards)) {
//    		if (isFirstCardTrumpf(cards)) ;
//  
//    	}
//    	
//    	
//    	//Methoden:
//    	// highestTrumpf - isFirstCardTrumpf - isTrumpf
//    	
//    	
//    	return null;
//    	//Ist eine der Karten Trumpf
//    }

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

	private boolean isFirstCardTrumpf(ArrayList<Card> cards) {
		boolean found = false;
		if (cards.get(0).getSuit().toString() == this.trumpf) found = true;
		return found;
	}

	private String isSuit(ArrayList<Card> cards) {
		//welche Frabe hat die erste Karte
		String suit = cards.get(0).getSuit().toString();
		//Noch keine Rückgabe!!!!!
		return suit;
	}
	
	private boolean isTrumpf(ArrayList<Card> cards) {
		// this.trumpf muss noch geändert werden....
		// alle Karten werden geprüft ob mindestens 1 Karte Trumpf ist
		boolean found = false;
		for (int i = 0; i < cards.size() && !found; i++) {
		//	for (int j = i+1; j < cards.size() && !found; j++) {
				if (cards.get(i).getSuit().toString() == this.trumpf) found = true;
		//	}
		}
		return found;
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
	@Override
	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		return 0;
	}
    

    
    
    
    
}
