package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultSendTableCard extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	

	public ResultSendTableCard(boolean result) {
		super(new String[] {"ResultSendTableCard", Boolean.toString(result)});
	}
	
	public ResultSendTableCard(String[] content) {
		super(content);
	}
	
	@Override
	public void process(JassClientController controller) {
		
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.SomethingFailed();
	}
}

