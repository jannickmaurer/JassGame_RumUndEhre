package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultJoinPlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String owner;
	private String maxPoints;
	
	public ResultJoinPlayroom(boolean result) {
		super(new String[] {"ResultJoinPlayroom", Boolean.toString(result)});
	}
	
	public ResultJoinPlayroom(String[] content) {
		super(content);
		if(content.length > 2) {
			this.owner = content[2];
			this.maxPoints = content[3];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		
	}
	
	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.SomethingFailed();
	}

}
