package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultBroadcastSendTableCard extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String name;
	private String tableCard;

	public ResultBroadcastSendTableCard(String[] content) {
		super(content);
		if(content.length > 2) {
			this.name = content[1];
			this.tableCard = content[2];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.SomethingFailed();
	}
}
