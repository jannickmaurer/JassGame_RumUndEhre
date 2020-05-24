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
public class ResultDeleteAccount  extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	public ResultDeleteAccount(boolean result) {
		super(new String[] {"ResultDeleteAccount", Boolean.toString(result)});
	}
	
	public ResultDeleteAccount(String[] content) {
		super(content);
	}
	@Override
	public void process(JassClientController controller) {
		controller.setAccount(null);
		controller.logoutSuccess();
	}

	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}

}
