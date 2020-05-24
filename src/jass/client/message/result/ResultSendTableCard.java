package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.Card;
import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Individual message as response for client -> server message
 * Triggers events in controller depending on message success (true/false)
 */
public class ResultSendTableCard extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String playedCard;

	public ResultSendTableCard(boolean result) {
		super(new String[] {"ResultSendTableCard", Boolean.toString(result)});
	}
	
	public ResultSendTableCard(String[] content) {
		super(content);
		if(content.length > 2) this.playedCard = content[2];
	}
	
	@Override
	public void process(JassClientController controller) {
		logger.info("Remove Handcard: " + playedCard) ;
		controller.getBoard().removeHandCard(new Card(playedCard));
		controller.updatePlayerPane(playedCard);
		controller.getView().getSpielraumLayout().getPlayerPane().setDisabledCardLabels(true);
	}

	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}
}

