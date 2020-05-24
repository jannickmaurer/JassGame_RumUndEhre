package jass.commons;

import jass.commons.Card;

/**
 * David Schürch
 * Klasse übernommen von PokerGame, leichte individuelle Anpassungen
 */

public class Card implements Comparable<Card>{
	

    public enum Suit { Clubs, Diamonds, Hearts, Spades;
              
        public String toString() {
            String suit = "";
            switch (this) {
            case Clubs: suit = "C"; break;		
            case Diamonds: suit = "D"; break;
            case Hearts: suit = "H"; break;
            case Spades: suit = "S"; break;
            }
            return suit;
        }
    }
    
    public enum Rank { Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
        
        public String toString() {
            String str = "";
            int ordinal = this.ordinal();
            if (ordinal <= 3) {
                str = Integer.toString(ordinal+6);
            } else if (ordinal == 4) {
                str = "T";
            } else if (ordinal == 5) {
                str = "J";
            } else if (ordinal == 6) { 
                str = "Q";
        	} else if (ordinal == 7) { 
        		str = "K";
        	} else if (ordinal == 8) { 
        		str = "A";
        }
            return str;
        }
    }
    
    private Suit suit;
	private Rank rank;
    private String shortForm;
    
    public Card(String shortForm) {
        this.shortForm = shortForm;
        this.setSuit();
        this.setRank();
    }
    
    public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}
	
    public void setSuit() { 
    	switch(shortForm.charAt(0)){
    		case('C'): suit = Suit.Clubs; break;
    		case('D'): suit = Suit.Diamonds; break;
    		case('H'): suit = Suit.Hearts; break;
    		case('S'): suit = Suit.Spades; break;
    	}
      }
  
    public void setRank() {
    	switch(shortForm.charAt(1)){
    		case('6'): rank = Rank.Six; break;
    		case('7'): rank = Rank.Seven; break;
    		case('8'): rank = Rank.Eight; break;
    		case('9'): rank = Rank.Nine; break;
    		case('T'): rank = Rank.Ten; break;
    		case('J'): rank = Rank.Jack; break;
    		case('Q'): rank = Rank.Queen; break;
    		case('K'): rank = Rank.King; break;
    		case('A'): rank = Rank.Ace; break;
    	}
    }

    public String toString() {
        return shortForm;
    }
 
   	public int compareTo(Card card) {
		if(this.getRank().compareTo(card.getRank()) > 0 ) {
			return 1;
		} else if(this.getRank().compareTo(card.getRank()) < 0) {
				return -1;
			} 
		return 0;
	}
}

