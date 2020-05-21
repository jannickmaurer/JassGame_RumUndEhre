package jass.commons;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class BoradTest {
	
	@Test //brauchts für linke seite
	void checkPlayerListener() {
		Board samstingsJass = new Board("samistigsJass", "Trumpf");
		
		assertEquals(1, Board.playersTurn);
		samstingsJass.playerListener(3);
		assertEquals(3, Board.playersTurn);		
	}
	
	@Test
	public void checkCardListener() {
		Board samstingsJass = new Board("samistigsJass", "Trumpf");
//		samstingsJass.cardListener("6D|8H|TD");
		assertEquals("6D|8H|TD", samstingsJass.tableCards.toString());
	}

	@Test
	public void checkHandCards() {
		Board samstingsJass = new Board("samistigsJass", "Trumpf");
		assertEquals("D6|D7|DA|SQ|CT|S6|HJ|HK|S7", samstingsJass.handCards.toString());
	}
	
}
