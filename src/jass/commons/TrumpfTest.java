package jass.commons;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TrumpfTest {
	
//	@Test //brauchts für linke seite
//	void checkPlayerListener() {
//		Board samstingsJass = new Board("samistigsJass", "Trumpf");
//		
//		assertEquals(1, Board.playersTurn);
//		samstingsJass.playerListener(3);
//		assertEquals(3, Board.playersTurn);		
//	}
//	
//	@Test
//	public void checkCardListener() {
//		Board samstingsJass = new Board("samistigsJass", "Trumpf");
//		samstingsJass.cardListener("6D|8H|TD");
//		assertEquals("6D|8H|TD", samstingsJass.tableCards.toString());
//	}

	@Test
	public void checkGetPoints() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card("D6"));
		cards.add(new Card("C9"));
		cards.add(new Card("CA"));
		cards.add(new Card("CJ"));
		
//		int points = Trumpf.getPoints(cards);
		
//		Board samstingsJass = new Board("samistigsJass", "Trumpf");
		assertEquals(24, Trumpf.getPoints(cards));
	}
	
}
