package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultLogout  extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	public ResultLogout(boolean result) {
		super(new String[] {"ResultLogout", Boolean.toString(result)});
	}
	
	public ResultLogout(String[] content) {
		super(content);
	}
	@Override
	public void process(JassClientController controller) { //Changed from JassClientModel model to controller
		controller.LogoutSuccess();
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.SomethingFailed();
	}
}
