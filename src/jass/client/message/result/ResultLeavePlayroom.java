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
public class ResultLeavePlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	
	public ResultLeavePlayroom(boolean result) {
		super(new String[] {"ResultLeavePlayroom", Boolean.toString(result)});
	}
	
	public ResultLeavePlayroom(String[] content) {
		super(content);
	}
	
	@Override
	public void process(JassClientModel model) {
	}
	
	public void processIfFalse(JassClientController controller) {
		controller.somethingFailed();
	}
}
