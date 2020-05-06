package jass.commons;

import jass.commons.Card;
import jass.commons.Card.Rank;
import jass.commons.Card.Suit;

public class Card implements Comparable<Card>{
	

	

    public enum Suit { Clubs, Diamonds, Hearts, Spades;
        @Override        
        public String toString() {
            String suit = "";
            switch (this) {
            case Clubs: suit = "C"; break;		//Änderung auf C von Clubs etc.
            case Diamonds: suit = "D"; break;
            case Hearts: suit = "H"; break;
            case Spades: suit = "S"; break;
            }
            return suit;
        }
    };
    
    public enum Rank { Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
        @Override
        public String toString() {
        	String str = Integer.toString(this.ordinal()+1); //Gibt Rank als Zahl von 1 bis 9 zurück
            return str;
        }
    };
    
    private final Suit suit;
    private final Rank rank;
    
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    
    // Method to compare cards by rank
	public int compareTo(Card o) {
		if(this.getRank().compareTo(o.getRank()) > 0 ) {
			return 1;
		} else if(this.getRank().compareTo(o.getRank()) < 0) {
				return -1;
			} 
		return 0;
	}

}

