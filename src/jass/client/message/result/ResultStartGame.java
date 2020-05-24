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
public class ResultStartGame extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String token;

	public ResultStartGame(boolean result) {
		super(new String[] {"ResultStartGame", Boolean.toString(result)});
	}
	public ResultStartGame(String[] content) {
		super(content);
	}
	
	@Override
	public void process(JassClientController controller) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller.startGameSuccess();
	}

	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}
}
