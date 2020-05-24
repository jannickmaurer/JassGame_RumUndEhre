package jass.client.message.result;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Individual message as response for client -> server message
 * Triggers events in controller depending on message success (true/false)
 */
public class ResultShuffle extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String handCardsAsString;
	private ArrayList<String> tableCards;

	public ResultShuffle(boolean result) {
		super(new String[] { "ResultShuffle", Boolean.toString(result) });
	}

	public ResultShuffle(String[] content) {
		super(content);
		if (content.length > 1) {
			this.handCardsAsString = content[1];
			for (int i = 2; i < content.length; i++)
				this.handCardsAsString += "|" + content[i];
		}
	}

	@Override
	public void process(JassClientController controller) {
		controller.getBoard().shuffledCardListener(handCardsAsString);
		controller.updatePlayerPane();
	}

	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}
}
