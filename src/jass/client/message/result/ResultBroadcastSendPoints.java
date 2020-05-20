package jass.client.message.result;

import java.util.ArrayList;
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
	private String currentLeader;
	private ArrayList<String> players;
	private ArrayList<Integer> points;

	public ResultBroadcastSendPoints(String[] content) {
		super(content);
		if(content.length > 1) {
			this.currentLeader = content[1];
			for(int i = 2; i < content.length; i++) {
				String[] temp = content[i].split("\\:");
				players.add(temp[0]);
				points.add(Integer.parseInt(temp[1]));
			}
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		
	}

	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}
}
