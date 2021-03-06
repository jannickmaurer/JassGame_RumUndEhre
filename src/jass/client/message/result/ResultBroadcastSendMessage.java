package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Message sent to all members in a playroom
 * Chat (text) message
 * Sent everytime a member sends a chatmessage
 */
public class ResultBroadcastSendMessage extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String name;
	private String message;

	public ResultBroadcastSendMessage(String[] content) {
		super(content);
		if(content.length > 1) {
			this.name = content[1];
			this.message = content[2];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.updateChatText(this.name + ": " + this.message);
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}
}
