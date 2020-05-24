package jass.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.Card;
<<<<<<< HEAD
=======
import jass.commons.Cards;
import jass.commons.ServiceLocator;
>>>>>>> branch 'master' of https://github.com/jannickmaurer/JassGame_RumUndEhre.git
import jass.commons.Trumpf;

<<<<<<< HEAD
public class ServerTableCards {
=======
public class ServerTableCards extends Cards{
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
>>>>>>> branch 'master' of https://github.com/jannickmaurer/JassGame_RumUndEhre.git

	ArrayList<Card> serverTableCards = new ArrayList<>();
	ArrayList<String> usernames = new ArrayList<>();

	public ServerTableCards() {
		super();
	}

//	public ArrayList<Card> getServerTableCards() {
//		return serverTableCards;
//	}
	
//	public boolean hasServerTableCards() {
//		return serverTableCards.size() != 0; 
//	}

	public boolean isComplete() { // brauchts die hier???
		return serverTableCards.size() == 4;
	}
	

	public int getPoints(String handType) {
		return Trumpf.getPoints(serverTableCards, handType);
	}
	
	public int getTrumpfPoints(String trumpf) {
		return Trumpf.getTrumpfPoints(serverTableCards, trumpf);
	}
	
	
//	public int getPoints(String handType, String trumpf) {
//		return Trumpf.getPoints(serverTableCards, handType, trumpf);
//	}
	
	public Card getCard(int i) {
		return serverTableCards.get(i);
	}
	
	public void addClientCard(String clientCard) {
		String[] temp = clientCard.split("\\:");
		usernames.add(temp[0]);
		serverTableCards.add(new Card(temp[1]));
	}
	
	public void clearServerTableCards() {
		serverTableCards.clear();
	}
	
	public Card getHighestUfeAbeCard(String gameType) {
		return Trumpf.highestUfeAbeCard(serverTableCards, gameType);
	}
	
	public Card getHigherSameSuitCard() {
		return Trumpf.higherSameSuitCard(serverTableCards);
	}
	
	public Trumpf evaluateTrumpf() {
		return Trumpf.evaluateTrumpf(serverTableCards);
	}
	
	public Card getHighestTrumpfCard() {
		return  Trumpf.highestTrumpf(serverTableCards);
	}
	
	public int hasLength() {
		return serverTableCards.size();
	}
	
//*********
	public String getUsername(int place) {
		return usernames.get(place);
	}
//*********





}
