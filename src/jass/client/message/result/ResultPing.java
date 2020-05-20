package jass.client.message.result;

import java.util.ArrayList;
import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;
import jass.server.Client;


// NOT YET DONE! 

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
	}
	
	public void processIfFalse(JassClientController jassClientController) {
		// TODO Auto-generated method stub
		
	}
}

