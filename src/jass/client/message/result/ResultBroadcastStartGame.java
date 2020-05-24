package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Message sent to all members in a playroom
 * Sent everytime the owner starts the game
 */
public class ResultBroadcastStartGame extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String maxPoints;

	public ResultBroadcastStartGame(String[] content) {
		super(content);
		this.maxPoints = content[1];
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.createBoard();
		controller.maxPoints(maxPoints);
	}

	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}
}
