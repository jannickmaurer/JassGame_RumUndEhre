package jass.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;

public class Playroom {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private String name;
	private int MAX_MEMBER = 4;
	private int MIN_MEMBER = 2;
	ArrayList<Account> members;
	private Chatroom chatroom;
	private boolean gameStarted = false;
	private Account playerOnTurn;
	
	
	
	
	public static void endGame() {
		
	}
	
	
}
