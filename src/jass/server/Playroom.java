package jass.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;

/* Playroom represents an abstract concept where players get together and start to play
 * Playrooms are created by a client and the client is the owner, only the owner can start the game
 * But in fact, only specific Gametypes can be created (Trumpf, Schieber etc.)
 * This concept contains all information, variables and methods that all the gametypes share
 * A "Player" in a Playroom is represented by a "Client" Object bc this class represents best a "Player" in a technical view
 * Through the client we can always get the Account with which the client is logged in in order to get more information (username etc.)
 */

public abstract class Playroom {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String name;
	private int MAX_MEMBER = 4;
	private int MIN_MEMBER = 2;
	ArrayList<Client> members;
	private Chatroom chatroom;
	private boolean gameStarted = false;
	private Client playerOnTurn;
	private Client owner;
	
	public void Playroom(String name, Client owner) {
		this.name = name;
		this.owner = owner;
		members.add(owner);
		this.chatroom = new Chatroom(Playroom.this);
	}
	
	//add new member to the playroom and add the member to playroom's chatroom
	public void addMember(Client member) {
		members.add(member);
		chatroom.addMember(member);
	}
	
	public static void endGame() {
		
	}
	
	// Getters and Setters:
	
	public ArrayList<Client> getMembers(){
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

	public Client getPlayerOnTurn() {
		return playerOnTurn;
	}

	public void setPlayerOnTurn(Client playerOnTurn) {
		this.playerOnTurn = playerOnTurn;
	}

	public Client getOwner() {
		return owner;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}
	
	
	
	
}
