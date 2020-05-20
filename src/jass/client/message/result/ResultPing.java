package jass.client.message.result;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;
import jass.server.Client;


/*
 * Class developed by Jannick
 * Individual message as response for client -> server message
 * Triggers events in controller depending on message success (true/false)
 */
public class ResultPing extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String usecase;
	
	public ResultPing(boolean result) {
		super(new String[] {"ResultPing", Boolean.toString(result)});		
	}

	public ResultPing(String[] content) {
		super(content);
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.getModel().setConnected(true);
		controller.getView().getBtnStart().setDisable(false);
	}
	
	public void processIfFalse(JassClientController jassClientController) {
		// TODO Auto-generated method stub
		
	}
}

