package jass.server;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.commons.ServiceLocator;
import jass.message.Message;

public class Chatroom {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	private ArrayList<Client> members;
	
	public Chatroom(Playroom playroom) {
		for(Client c : playroom.getMembers()) {
			members.add(c);
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
	
}
