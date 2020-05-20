package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultDeletePlayroom  extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();

	public ResultDeletePlayroom(boolean result) {
		super(new String[] {"ResultDeletePlayroom", Boolean.toString(result)});
	}
	
	public ResultDeletePlayroom(String[] content) {
		super(content);
	}
	@Override
	public void process(JassClientModel model) {
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}

}
