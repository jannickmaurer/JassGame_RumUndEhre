package jass.server;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
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
	
	public void removeMember(String member) {
		members.remove(member);
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
	
	public static void add(Chatroom chatroom) {
		chatrooms.add(chatroom);
	}
	
	public static void remove(Chatroom chatroom) {
		chatrooms.remove(chatroom);
	}
	
	
	
}

