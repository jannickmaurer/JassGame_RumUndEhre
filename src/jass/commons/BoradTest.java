//package jass.commons;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//
//class BoradTest {
//	
//	@Test //brauchts für linke seite
//	void checkPlay() {
//		Board samstingsJass = new Board("samistigsJass", "Trumpf");
//		ArrayList<Card> handCards = new ArrayList<>();
//		handCards.add(new Card("6D"));
//		handCards.add(new Card("7D"));
//		handCards.add(new Card("8D"));
//		handCards.add(new Card("6H"));
//		handCards.add(new Card("TC"));
//		samstingsJass.handCards.setHandCards(handCards);
//		
//		ArrayList<Card> playableHandCards = new ArrayList<>();
//		handCards.add(new Card("6D"));
//		handCards.add(new Card("7D"));
//		handCards.add(new Card("8D"));
////		handCards.add(new Card("6H"));
////		handCards.add(new Card("TC"));
//		samstingsJass.handCards.setPlayableHandCards(playableHandCards);
//		
//		
//		
////		handCards.setHandCards(cards);
//		
////		assertEquals("6D", Board.play();
////		samstingsJass.playerListener(3);
////		assertEquals(3, Board.playersTurn);		
//	}
//	
//	@Test
//	public void checkCardListener() {
//		Board samstingsJass = new Board("samistigsJass", "Trumpf");
////		samstingsJass.cardListener("6D|8H|TD");
////		assertEquals("6D|8H|TD", samstingsJass.tableCards.toString());
//	}
//
//	@Test
//	public void checkHandCards() {
//		Board samstingsJass = new Board("samistigsJass", "Trumpf");
//		assertEquals("D6|D7|DA|SQ|CT|S6|HJ|HK|S7", samstingsJass.handCards.toString());
//	}
//	
//}
