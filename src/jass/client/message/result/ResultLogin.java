package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultLogin extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;

	public ResultLogin(boolean result) {
		super(new String[] {"ResultLogin", Boolean.toString(result)});
	}
	public ResultLogin(String[] content) {
		super(content);
		this.token = content[2];
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.getModel().setToken(this.token);
	}
	
	public void processIfFalse(JassClientController jassClientController) {
		// TODO Auto-generated method stub
		
	}
}
