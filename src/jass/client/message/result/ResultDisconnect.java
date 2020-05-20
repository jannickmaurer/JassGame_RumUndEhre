package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultDisconnect  extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	public ResultDisconnect(boolean result) {
		super(new String[] {"ResultDisconnect", Boolean.toString(result)});
	}
	
	public ResultDisconnect(String[] content) {
		super(content);
	}
	@Override
	public void process(JassClientController controller) {
		controller.getModel().closeSocket();
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}
}
