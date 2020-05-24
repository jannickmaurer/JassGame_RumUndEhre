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
public class ResultCreatePlayroom  extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	public ResultCreatePlayroom(boolean result) {
		super(new String[] {"ResultCreatePlayroom", Boolean.toString(result)});
	}
	
	public ResultCreatePlayroom(String[] content) {
		super(content);
	}
	@Override
	public void process(JassClientController controller) {
		controller.listPlayrooms();
	}
	
	public void processIfFalse(JassClientController controller) {
		controller.somethingFailedCreatePlayroom();
	}


}
