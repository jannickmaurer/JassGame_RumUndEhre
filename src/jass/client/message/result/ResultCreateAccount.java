package jass.client.message.result;

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
public class ResultCreateAccount extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	

	public ResultCreateAccount(boolean result) {
		super(new String[] {"ResultCreateAccount", Boolean.toString(result)});
	}
	
	public ResultCreateAccount(String[] content) {
		super(content);
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.autologin();
	}
	
	public void processIfFalse(JassClientController controller) {
		controller.somethingFailedRegistration();
	}

}
