package jass.client.message.result;

import java.util.logging.Logger;

import jass.client.controller.JassClientController;
import jass.client.model.JassClientModel;
import jass.commons.ServiceLocator;
import jass.message.Message;
import javafx.application.Platform;

/*
 * Class developed by Jannick
 * Individual message as response for client -> server message
 * Triggers events in controller depending on message success (true/false)
 */
public class ResultJoinPlayroom extends Message {
	private static ServiceLocator sl = ServiceLocator.getServiceLocator();
	private static Logger logger = sl.getServerLogger();
	private String owner;
	private String name;
//	private String gameType;
	
	public ResultJoinPlayroom(boolean result) {
		super(new String[] {"ResultJoinPlayroom", Boolean.toString(result)});
	}
	
	public ResultJoinPlayroom(String[] content) {
		super(content);
		if(content.length > 2) {
			this.owner = content[2];
			this.name = content[3];
//			this.gameType = content[4];
		}
	}
	
	@Override
	public void process(JassClientController controller) {
		controller.setCurrentPlayroom(name);
//		controller.setCurrentGameType(gameType);
		controller.joinSuccess();
	}
	
	public void processIfFalse(JassClientController controller) {
		// TODO Auto-generated method stub
		controller.somethingFailed();
	}

	
}
