package jass.commons;

import java.util.ArrayList;
import java.util.logging.Logger;
import jass.client.TableCards;
import jass.client.HandCards;
import jass.client.PlayableHandCards;

public class Board {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	public static int playersTurn;
	public int imPlayer;
	public static String trumpf = "H";
	public boolean wiisDone = false;
	public Card myLastPlayedCard;
	public String gameTyp = "Trumpf";
//	public String playableCards;
	public PlayableHandCards playableHandCards; //HandCards
	public HandCards handCards; //allefalls direkt zugriff
	private TableCards tableCards;
//	public HandCards startHandCards;
//	private String playerOnTurn;
	private int playedCards;
	private ArrayList<String> members = new ArrayList<>();
	
	public Board(String roomName, String gameTyp) {
		this.handCards = new HandCards();
		this.tableCards = new TableCards();
		this.gameTyp = gameTyp;
		this.playedCards = 0;
		this.playableHandCards = new PlayableHandCards();
	}
	
	public Board() {
		this.handCards = new HandCards();
		this.tableCards = new TableCards();
//		this.gameTyp = gameTyp;
		this.playedCards = 0;
		this.playableHandCards = new PlayableHandCards();
	}

	public void shuffledCardListener(String newHandCards) {
		ArrayList<Card> tempHandCards = new ArrayList<>();
		logger.info(newHandCards);
		String[] handCardList = newHandCards.split("\\|");
		for (int i = 0; i < handCardList.length; i++) {
			tempHandCards.add(new Card(handCardList[i]));
		}
		handCards.setHandCards(tempHandCards);
		logger.info("HandCards added: " + handCards.toString());
		
	}

	//Methode play evaluiert nur welche Karten gespielt werden dürfen und welche nicht
	public void play() {
		if(playableHandCards.hasCards()) playableHandCards.clearPlayableHandCards();
		if (tableCards.hasCards() == false) {
			playableHandCards.setPlayableHandCards(handCards.getHandCards());
		}
		if (tableCards.hasCards() == true) {
			if (gameTyp.equals("Trumpf")) {
				if (tableCards.evaluateTrumpf().toString().equals("Trumpf")) {
					if (handCards.hasTrumpfCards()) {
						for (int i = 0; i < handCards.hasLength(); i++) {
							if (handCards.getCardSuit(i).toString().equals(trumpf)) {
								playableHandCards.add(handCards.getCard(i));
							}
						}		
					}else playableHandCards.setPlayableHandCards(handCards.getHandCards());
				}
				if (tableCards.evaluateTrumpf().toString().equals("Stich")) {
					Card highestTableStichCard;
					if (handCards.hasTrumpfCards()) {
						highestTableStichCard = tableCards.getHighestTrumpfCard();
						ArrayList<Card> tempHigherThanStichCards = new ArrayList<Card> ();
						tempHigherThanStichCards = handCards.getCardHigherThanStich(highestTableStichCard);
						if (!tempHigherThanStichCards.isEmpty()) {
							for (Card card : tempHigherThanStichCards) {
								playableHandCards.add(card);
							}
						}
						if (handCards.hasSameSuitCard(tableCards.getCard(0))) {
							for (int i = 0; i < handCards.hasLength(); i++) {
								if(handCards.getCardSuit(i).equals(tableCards.getCardSuit(0))) {
									playableHandCards.add(handCards.getCard(i));
								}
							}
						} else {
							for (int i = 0; i < handCards.hasLength(); i++) {
								if(!handCards.getCardSuit(i).equals(tableCards.getCardSuit(0)) &&
							 	   !handCards.getCardSuit(i).toString().equals(trumpf)) {
									playableHandCards.add(handCards.getCard(i));
								}
							}
						}
					}							
				}
				if (handCards.evaluateTrumpf().toString().equals("None")) {
					for (int i = 0; i < handCards.hasLength(); i++) {
						if(handCards.getCardSuit(i).equals(tableCards.getCardSuit(0))) {
							playableHandCards.add(handCards.getCard(i));
						}
					}
				}
				if (playableHandCards.hasPlayableCards() == false) {
					playableHandCards.setPlayableHandCards(handCards.getHandCards()); 		
				}
			}

			if (gameTyp.equals("ObeAbe") || gameTyp.equals("UndeUfe") || gameTyp.equals("Slalom")) {
				for (int i = 0; i < handCards.hasLength(); i++) {
					if(handCards.getCardSuit(i).equals(tableCards.getCardSuit(0))) {
						playableHandCards.add(handCards.getCard(i));
					}
				}	
				if (playableHandCards.hasPlayableCards() == false) {
					playableHandCards.setPlayableHandCards(handCards.getHandCards());
				}
			}
		} 

		
		
		logger.info("Play: " + playableHandCards + "  " + gameTyp + "  " + trumpf);
	}

	public void addTableCard(Card card) {
		logger.info("Add Card to TableCards: " + card.toString());
		this.tableCards.addTableCard(card);
		this.playedCards++;
		if(playedCards == members.size()) {
			playedCards = 0;
			tableCards.clearTableCards();
			logger.info("TableCards cleared");
		}
	}

	public int getImPlayer() {
		return imPlayer;
	}

	public void setImPlayer(int imPlayer) {
		this.imPlayer = imPlayer;
	}

	public static String getTrumpf() {
		return trumpf;
	}

	public static void setTrumpf(String trumpf) {
		Board.trumpf = trumpf;
	}

	public boolean isWiisDone() {
		return wiisDone;
	}

	public void setWiisDone(boolean wiisDone) {
		this.wiisDone = wiisDone;
	}

	public String getGameTyp() {
		return gameTyp;
	}

	public void setGameTyp(String gameTyp) {
		this.gameTyp = gameTyp;
	}

	public ArrayList<Card> getPlayableHandCards() {
		return playableHandCards.getPlayableHandCards();
	}

//Getter und Setter
	public HandCards getHandCards() {
		return handCards;
	}
	
	public ArrayList<Card> getHandCardsList() {
		return handCards.getHandCards();
	}

	public static void setPlayersTurn(int playersTurn) {
		Board.playersTurn = playersTurn;
	}
	
	public void removeHandCard(Card card) {
		logger.info("Board removes handcard: " + card.toString() );
			this.handCards.cardPlayed(card);
	}
	
	public void setMembers(ArrayList<String> members) {
		for(String m : members) {
			this.members.add(m);
		}
	}
	
	public TableCards getTableCards() {
		return this.tableCards;
	}
}
