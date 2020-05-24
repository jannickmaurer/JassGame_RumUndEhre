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
public class ResultBroadcastStartRound extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String gameType;
	private String trumpf;
	private String slalomStart;

	public ResultBroadcastStartRound(String[] content) {
		super(content);
		this.gameType = content[1];
		if(this.gameType.equals("Trumpf")) this.trumpf = content[2];
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.setCurrentGameType(gameType);
		if(gameType.equals("Trumpf")) controller.getBoard().setTrumpf(trumpf);
		controller.gameType(gameType);
		controller.trumpf(trumpf);
	}

	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}
}
