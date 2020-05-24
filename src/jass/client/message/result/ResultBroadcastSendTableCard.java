package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.Card;
import jass.commons.ServiceLocator;
import jass.message.Message;

/*
 * Class developed by Jannick
 * Message sent to all members in a playroom
 * Sent everytime a member sends ("Spielt") a card
 * Sends the played card to each member
 */
public class ResultBroadcastSendTableCard extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String name;
	private String tableCard;
	private String nextPlayer;

	public ResultBroadcastSendTableCard(String[] content) {
		super(content);
		if(content.length > 1) {
			this.name = content[1];
			this.tableCard = content[2];
			this.nextPlayer = content[3];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.updatePlayedCard(name, tableCard);
		controller.getBoard().addTableCard(new Card(tableCard));
		if(!nextPlayer.equalsIgnoreCase("null")) controller.setPlayerOnTurn(nextPlayer);
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}
}
