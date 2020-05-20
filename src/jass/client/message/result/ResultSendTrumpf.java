package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultSendTrumpf extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;

	public ResultSendTrumpf(boolean result) {
		super(new String[] {"ResultSendTrumpf", Boolean.toString(result)});
	}
	public ResultSendTrumpf(String[] content) {
		super(content);
	}
	
	@Override
	public void process(JassClientModel model) {
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}
}
