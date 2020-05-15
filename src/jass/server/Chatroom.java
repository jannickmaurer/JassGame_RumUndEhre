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
	
	private ArrayList<Client> members;
	
	public Chatroom(Playroom playroom) {
		for(Client c : playroom.getMembers()) {
			members = new ArrayList<>();
			members.add(c);
			this.playroom = playroom;
		}
	}
	
	public void addMember(Client member) {
		members.add(member);
	}

	public void send(Message msg) {
		for(Client c : members) {
			c.send(msg);
		}
	}
	
	public void add(Chatroom chatroom) {
		chatrooms.add(chatroom);
	}
	
}

