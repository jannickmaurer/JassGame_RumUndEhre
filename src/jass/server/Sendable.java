package jass.server;

import jass.message.Message;

// Copieed from Chatroom project, don't know wtf this is for :-D

public interface Sendable {
	public abstract String getName(); // The name of the target being sent a message
	public abstract void send(Message msg); // The method to sent to this target
}
