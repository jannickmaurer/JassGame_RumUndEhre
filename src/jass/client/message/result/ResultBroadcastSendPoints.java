package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

/* Message to be sent to all playroom members individually 
 * Conatins current points of each player and currentLeader
 */
public class ResultBroadcastSendPoints extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private int points;
	private String currentLeader;

	public ResultBroadcastSendPoints(String[] content) {
		super(content);
		if(content.length > 2) {
			this.points = Integer.parseInt(content[1]);
			this.currentLeader = content[2];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		
	}

	public void processIfFalse(JassClientController jassClientController) {
		
	}
}
