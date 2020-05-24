package jass.commons;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import jass.client.HandCards;
import jass.server.EvaluationRuleSet;
import jass.server.ServerTableCards;

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

//	@Test
//	public void checkHighestUfeAbeSlalom() {
//		ArrayList<Card> cards = new ArrayList<Card>();
//		cards.add(new Card("CT"));
//		cards.add(new Card("C6"));
//		cards.add(new Card("CA"));
//		cards.add(new Card("CK"));
//		String gameTyp = "UndeUfe";
//		
//		assertEquals("CJ", Trumpf.highestUfeAbeCard(cards, gameTyp));
//	}
	
	
	@Test
	public void checkCardPlayed() {
		HandCards checkCards = new HandCards();
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card("HJ"));
		cards.add(new Card("CA"));
		cards.add(new Card("D6"));
		cards.add(new Card("CK"));
		
		checkCards.setHandCards(cards);
	
		
//		assertEquals("CJ", checkCards.cardPlayed(new Card("D6")));

	}
	
//	@Test //brauchts für linke seite
//	void checkPlayerListener() {
//		Board samstingsJass = new Board("samistigsJass", "Trumpf");
//		
//		assertEquals(1, Board.playersTurn);
//		samstingsJass.playerListener(3);
//		assertEquals(3, Board.playersTurn);		
//	}
	
//	@Test
//	public void checkGetPoints() {
//		ArrayList<Card> cards = new ArrayList<Card>();
//		cards.add(new Card("D6"));
//		cards.add(new Card("C9"));
//		cards.add(new Card("CA"));
//		cards.add(new Card("CJ"));
//
//		assertEquals(24, Trumpf.getPoints(cards));
//	}
	
}
