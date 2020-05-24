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
public class ResultLogin extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;
	private String username;

	public ResultLogin(boolean result) {
		super(new String[] {"ResultLogin", Boolean.toString(result)});
	}
	public ResultLogin(String[] content) {
		super(content);
		if(content.length > 2) {
			this.token = content[2];
			this.username = content[3];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.loginSuccess();
		controller.getModel().setToken(this.token);
		controller.setAccount(username);
		controller.listPlayrooms();
	}
	
	public void processIfFalse(JassClientController controller) {
		controller.somethingFailedLogin();
	}
}
