package jass.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;
import jass.message.Message;

public class Chatroom implements Serializable {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private static final ArrayList<Chatroom> chatrooms = new ArrayList<>();
	private Playroom playroom;
	
	private ArrayList<String> members;
	
	public Chatroom(Playroom playroom) {
		members = new ArrayList<>();
		this.playroom = playroom;
		for(String s : playroom.getMembers()) {
			members.add(s);
		}
	}
	
	public void addMember(String member) {
		members.add(member);
	}

	public void send(Message msg) {
		for(String c : members) {
			Client.getClient(c).send(msg);
		}
	}
	
	public static void add(Chatroom chatroom) {
		chatrooms.add(chatroom);
	}
	
}

