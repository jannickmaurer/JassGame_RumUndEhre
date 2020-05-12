package jass.commons;

import jass.commons.Card;
import jass.commons.Card.Rank;
import jass.commons.Card.Suit;
import javafx.scene.image.Image;

public class Card implements Comparable<Card>{
	
//getter und setter für Bild der Karte mit if drinnen für Spracheinstellung
	

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
    
    private Suit suit;
    private Rank rank;
    private String shortForm;
    
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    public Card(String shortForm) {
        this.shortForm = shortForm;
    }
    
    

    public Suit getSuit() {
    	//TODO converntieren zu enums
    	//string splitten mit 1 und zweite zahl/buchstabe -> googlen 
    	//Danach mit Case auf neum matchen
    	//case (shortForm.fistLetter()) case c = suit.Clubs
    	//
        return suit;
    }
    
    public Image getCardPicture() {
    	if("d".equals("d")) {  //TODO sprache finden und individuell De oder FR karte zurück geben
    		return null;
    	}
    	return null;
    }

    public Rank getRank() {
    	//TODO converntieren zu enums
    	//string splitten mit 1 und zweite zahl/buchstabe -> googlen 
    	//Danach mit Case auf neum matchen
        return rank;
    }
    
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    
    // Method to compare cards by rank -- Noch nicht gebraucht
	public int compareTo(Card o) {
		if(this.getRank().compareTo(o.getRank()) > 0 ) {
			return 1;
		} else if(this.getRank().compareTo(o.getRank()) < 0) {
				return -1;
			} 
		return 0;
	}

}

