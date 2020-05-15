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

import jass.commons.ServiceLocator;

/* Playroom represents an abstract concept where players get together and start to play
 * Playrooms are created by a client and the client is the owner, only the owner can start the game
 * But in fact, only specific Gametypes can be created (Trumpf, Schieber etc.)
 * This concept contains all information, variables and methods that all the gametypes share
 * A "Player" in a Playroom is represented by a "Client" Object bc this class represents best a "Player" in a technical view
 * Through the client we can always get the Account with which the client is logged in in order to get more information (username etc.)
 */

public abstract class Playroom implements Serializable {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private static final ArrayList<Playroom> playrooms = new ArrayList<>();
	
	private String name;
	private int MAX_MEMBER = 4;
	private int MIN_MEMBER = 2;
	private ArrayList<String> members;
	private Chatroom chatroom;
	private boolean gameStarted = false;
	private String playerOnTurn;
	private String owner;

	public Playroom(String name, String owner) {
		this.name = name;
		this.owner = owner;
		members = new ArrayList<>();
		members.add(owner);
		this.chatroom = new Chatroom(Playroom.this);
		Chatroom.add(this.chatroom);
	}
	
	//add new member to the playroom and add the member to playroom's chatroom
	public void addMember(String member) {
		members.add(member);
		//Chatroom
	}
	
	public void removeMember(String username) {
		members.remove(username);
	}
	
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

	
	public static void endGame() {
		
	}
	
	public static Playroom getPlayroom(String name) {
		synchronized (playrooms) {
			for (Playroom playroom : playrooms) {
				if (playroom.name.equals(name)) return playroom;
			}
		}
		return null;
	}
	
	
	public static void remove(Playroom playroom) {
		synchronized (playrooms) {
			for (Iterator<Playroom> i = playrooms.iterator(); i.hasNext();) {
				if (playroom == i.next()) i.remove();
			}
		}
	}
	
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
	
	// Getters and Setters:
	
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
		return gameStarted;
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public String getPlayerOnTurn() {
		return playerOnTurn;
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
	
	public static ArrayList<String> getPlayroomNames(){
		ArrayList<String> names = new ArrayList<>();
		for(Playroom p : playrooms) {
			names.add(p.getName());
		}
		return names;
	}
	
	public String toString() {
		return this.name;
	}
	
	
}
	
	

