package jass.commons;

import java.util.ArrayList;
import java.util.Locale;

import jass.client.TableCards;
import jass.commons.Card;
import jass.commons.Card.Rank;
import jass.commons.Card.Suit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card implements Comparable<Card>{
	
//getter und setter für Bild der Karte mit if drinnen für Spracheinstellung
	

    public enum Suit { Clubs, Diamonds, Hearts, Spades;
        @Override        
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
    };
    
    public enum Rank { Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;
        @Override
        public String toString() {
//        	String str = Integer.toString(this.ordinal()+6); //Gibt Rank als Zahl von 1 bis 9 zurück
//            return str;
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
        	} else if (ordinal == 7) { 
        		str = "A";
        }
            return str;
        }
    };
    
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
	
    public void setSuit() { //String card
//    	//TODO converntieren zu enums
//    	//string splitten mit 1 und zweite zahl/buchstabe -> googlen 
//    	//Danach mit Case auf neum matchen
//    	//case (shortForm.fistLetter()) case c = suit.Clubs
 //   	String card = new String("6D");

    	switch(shortForm.charAt(0)){
    		case('C'): suit = Suit.Clubs; break;
    		case('D'): suit = Suit.Diamonds; break;
    		case('H'): suit = Suit.Hearts; break;
    		case('S'): suit = Suit.Spades; break;
    	}
        this.suit = suit;
    }
//    
    public void setRank() {// String card
//    	//TODO converntieren zu enums
//    	//string splitten mit 1 und zweite zahl/buchstabe -> googlen 
//    	//Danach mit Case auf enum matchen
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
    	this.rank = rank;
    }
    
    public Image getCardPicture(Card card) {
    	//TODO sprache finden und individuell De oder FR karte zurück geben
    	
    			String fileName = cardToFileName(card);
    			Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("jass/image/" + fileName));
//    			ImageView imv = new ImageView(image);
//    			imv.fitWidthProperty().bind(this.widthProperty());
//    			imv.fitHeightProperty().bind(this.heightProperty());
//    			imv.setPreserveRatio(true);
//    			this.setGraphic(imv);
//    		} else {
//    			this.setGraphic(null);
//    		}
    		return image;
    }
    
	private String cardToFileName(Card card) {
		String rank = card.getRank().toString();
		String suit = card.getSuit().toString();
		String locale = "FR";  //Wie greife ich aud eine Locale zu?????
		return rank + "_of_" + suit + "_" + locale +".jpg";
	}

    
    @Override
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

