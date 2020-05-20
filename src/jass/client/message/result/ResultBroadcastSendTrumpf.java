package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultBroadcastSendTrumpf extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String trumpf;


	public ResultBroadcastSendTrumpf(String[] content) {
		super(content);
		this.trumpf = content[1];
		}
	
	@Override
	public void process(JassClientController controller) {
		controller.getBoard().setTrumpf(trumpf);
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}
}
