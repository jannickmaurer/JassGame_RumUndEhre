package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

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
	public void process(JassClientModel model) {
		
	}
	
	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.SomethingFailed();
	}

}
