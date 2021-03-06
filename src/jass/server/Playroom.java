package jass.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import jass.client.message.result.ResultBroadcastSendPoints;
import jass.commons.Card;
import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Some concepts are inspired by or according to Software Engineering Chat Project
 */

public class Playroom implements Serializable {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private static final ArrayList<Playroom> playrooms = new ArrayList<>();
	
	private String name;
	private int MAX_MEMBER = 4;
	private int MIN_MEMBER = 2;
	private ArrayList<String> members;
	private Chatroom chatroom;
	private boolean gameRunning = false;
	private boolean roundEnded;
	private String playerOnTurn;
	private String owner;
	private int maxPoints;
	private int playedCards;
	private String gameType = "Trumpf";
	private EvaluationRuleSet evaluationRuleSet;
	private String tempLeader;
	private String[] content;
	private String trumpf = "H";
	
	
	/* Constructor: Open a new playroom with playroom name and owner account as String – maxPoints set by child class
	 * 
	 */
	public Playroom(String name, String owner) {
		this.name = name;
		this.owner = owner;
		this.evaluationRuleSet = new EvaluationRuleSet();
		this.members = new ArrayList<>();
		this.chatroom = new Chatroom(Playroom.this);
		this.playedCards = 0;
		Chatroom.add(this.chatroom);
	}
	
	public int getPlayedCards() {
		return playedCards;
	}

	public void setPlayedCards(int playedCards) {
		this.playedCards = playedCards;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
		evaluationRuleSet.setGameType(gameType);
	}

	public EvaluationRuleSet getEvaluationRuleSet() {
		return evaluationRuleSet;
	}

	public void setEvaluationRuleSet(EvaluationRuleSet evaluationRuleSet) {
		this.evaluationRuleSet = evaluationRuleSet;
	}

	/* Constructor: Open a new playroom with playroom name, owner account as String and maxPoints set by user
	 * 
	 */
	public Playroom(String name, String owner, int maxPoints) {
		this.name = name;
		this.owner = owner;
		this.maxPoints = maxPoints;
		members = new ArrayList<>();
		this.chatroom = new Chatroom(Playroom.this);
		Chatroom.add(this.chatroom);
	}
	
	/*
	 * Playing stuff:
	 */
	
	public void startGame() {
		this.gameRunning = true;
	}
	public void endGame() {
		this.gameRunning = false;
	}	
	//Adds points to account by searching for username
	public void addPoints(String username, int points) {
		Account.getAccount(username).addPoints(points);
	}
	//Returns current points ofaccount by searching for username
	public void getPoints(String username) {
		Account.getAccount(username).getPoints();
	}
	public int getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}
	public void addClientCard(String clientCard) {
		logger.info("Game Playroom: " + this.gameType + " & " + this.trumpf);
		playedCards++;
		if (playedCards < this.getMembers().size()) {
			roundEnded = false;
			logger.info("ClientCard added to ServerTableCards: " + clientCard);
			evaluationRuleSet.addClientCard(clientCard);
		}
		if (playedCards == this.getMembers().size()) {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			evaluationRuleSet.addClientCard(clientCard);
			tempLeader = evaluationRuleSet.winnerIsPlayer();
			int points = evaluationRuleSet.pointsForWinner();
			content = new String[] {"ResultBroadcastSendPoints", tempLeader, Integer.toString(points)};
			this.send(new ResultBroadcastSendPoints(content));
			playedCards = 0;
			roundEnded = true;
			evaluationRuleSet.getServerTableCards().clearServerTableCards();
		}
	}
	public String getPlayerOnTurn(String lastPlayed) {
		playerOnTurn = null;
		int index = -1;
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).equals(lastPlayed)) index = i;
		}
		if(index == 0) playerOnTurn = members.get(1);
		
		if(index == 1) {
			if(members.size() > 2) {
				playerOnTurn = members.get(2);
			} else {
				playerOnTurn = members.get(0);
			}
			
		}
		if(index == 2) {
			if(members.size() > 3) {
				playerOnTurn = members.get(3);
			} else {
				playerOnTurn = members.get(0);
			}
		}
		if(index == 3) {
			playerOnTurn = members.get(0);
		}
		
		return playerOnTurn;
	}

	
	
	
	/*Admin Stuff:
	 * 
	 */
	
	//add new member to the playroom and add the member to playroom's chatroom
	public void addMember(String member) {
		members.add(member);
		this.chatroom.addMember(member);	
	}
	
	//Removee member from playroom and chatroom
	public void removeMember(String username) {
		this.chatroom.removeMember(username);
		members.remove(username);
	}
	
	//Remove member from every playroom he's potentially logged in 
	public static void removeMemberFromAny(String username) {
		for(Playroom p : Playroom.getPlayrooms()) {
			for(String s : p.getMembers()) {
				if(s.equals(username)) {
					p.removeMember(s);
				}
			}
		}
	}
	
	//add a new playroom to the list of playrooms
	public static void add(Playroom playroom) {
		playrooms.add(playroom);
		savePlayrooms();
	}
	
	//Remove a playroom
	public static void remove(Playroom playroom) {
		synchronized (playrooms) {
			playrooms.remove(playroom);
		}
	}
	
	//Returns the playroom object when searching with playroom name
	public static Playroom getPlayroom(String name) {
		synchronized (playrooms) {
			for (Playroom playroom : playrooms) {
				if (playroom.name.equals(name)) return playroom;
			}
		}
		return null;
	}
	
	//Save playrooms to disk – is called everytime a new playroom gets created
	public static void savePlayrooms() {
		File playroomFile = new File(Server.getDirectory() + "playrooms.sav");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(playroomFile))) {
			synchronized (playrooms) {
				out.writeInt(playrooms.size());
				for (Playroom playroom : playrooms) {
					out.writeObject(playroom);
				}
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			logger.severe("Unable to save playrooms: " + e.getMessage());
		}
	}
	
	//Read playrooms from disk – called whenever the sever starts
	public static void readPlayrooms() {
		File playroomFile = new File(Server.getDirectory() + "playrooms.sav");
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(playroomFile))) {
			int num = in.readInt();
			for (int i = 0; i < num; i++) {
				Playroom playroom = (Playroom) in.readObject();
				playrooms.add(playroom);
				logger.info("Loaded playroom " + playroom.getName());
			}
		} catch (Exception e) {
			logger.severe("Unable to read playrooms: " + e.getMessage());
		}
	}
	
	
	public void send(Message msg) {
		Iterator<String> i = members.iterator();
		for(String s : members) {
			System.out.println(s);
		}
		
		while (i.hasNext()) {
			String username = i.next();
			Client user = Client.getClient(username);
			if (user == null) i.remove();
			else // User exists
				user.send(msg);
		}
	}
	
	
	
	
	/* Getters and Setters:
	 * 
	 */
	public ArrayList<String> getMembers(){
		return members;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMAX_MEMBER() {
		return MAX_MEMBER;
	}

	public void setMAX_MEMBER(int mAX_MEMBER) {
		MAX_MEMBER = mAX_MEMBER;
	}

	public int getMIN_MEMBER() {
		return MIN_MEMBER;
	}

	public void setMIN_MEMBER(int mIN_MEMBER) {
		MIN_MEMBER = mIN_MEMBER;
	}

	public Chatroom getChatroom() {
		return chatroom;
	}

	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

	public boolean isGameStarted() {
		return gameRunning;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameRunning = gameStarted;
	}


	public void setPlayerOnTurn(String playerOnTurn) {
		this.playerOnTurn = playerOnTurn;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public static ArrayList<Playroom> getPlayrooms(){
		return playrooms;
	}
	
	//Returns a list of all existing playroom names as Strings
	public static ArrayList<String> getPlayroomNames(){
		ArrayList<String> names = new ArrayList<>();
		for(Playroom p : playrooms) {
			names.add(p.getName());
		}
		return names;
	}
	
	
	
	
	public String getTempLeader() {
		return tempLeader;
	}

	public void setTempLeader(String tempLeader) {
		this.tempLeader = tempLeader;
	}

	public String getTrumpf() {
		return trumpf;
	}

	public void setTrumpf(String trumpf) {
		this.trumpf = trumpf;
		evaluationRuleSet.setTrumpf(trumpf);
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	

	public String toString() {
		return this.name;
	}
	
	public Boolean hasRoundEnded() {
		return roundEnded;
	}
	
	
	
	
}