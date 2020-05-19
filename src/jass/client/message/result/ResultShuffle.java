package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;

public class ResultShuffle extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String username;

	public ResultShuffle(boolean result) {
		super(new String[] {"ResultLogin", Boolean.toString(result)});
	}
	public ResultShuffle(String[] content) {
		super(content);
		if(content.length > 2) {
			this.token = content[2];
			this.username = content[3];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.LoginSuccess();
		controller.getModel().setToken(this.token);
		controller.setAccount(username);
	}
	
	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.SomethingFailed();
	}
}
